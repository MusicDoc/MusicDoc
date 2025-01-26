package io.github.musicdoc.view.render;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.decoration.DecoratedItem;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.decoration.MusicalDecorationPosition;
import io.github.musicdoc.decoration.PedalDecoration;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.instrument.string.StringTuning;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.note.NoteTone;
import io.github.musicdoc.note.StemDirection;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.metre.BeatPosition;
import io.github.musicdoc.rhythm.rest.Rest;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLineBreak;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.score.section.ScoreSectionName;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tab.TabTone;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.layout.ViewStavesLayout;
import io.github.musicdoc.view.layout.ViewTextAlignment;
import io.github.musicdoc.view.layout.ViewTextProgression;
import io.github.musicdoc.view.layout.ViewTextType;
import io.github.musicdoc.view.model.ViewBlock;
import io.github.musicdoc.view.model.ViewCell;
import io.github.musicdoc.view.model.ViewColumn;
import io.github.musicdoc.view.model.ViewDocument;
import io.github.musicdoc.view.model.ViewItemContainer;
import io.github.musicdoc.view.model.ViewItemGroup;
import io.github.musicdoc.view.model.ViewItemText;
import io.github.musicdoc.view.model.ViewPage;
import io.github.musicdoc.view.model.ViewRow;
import io.github.musicdoc.view.reader.ScoreLineReader;
import io.github.musicdoc.view.reader.ScoreRowReader;

/**
 * Internal stateful implementation of {@link ViewRenderer} logic.
 */
class ViewRendererIntern implements ViewTextRenderer {

  /** The {@link ViewRenderAdapter}. */
  private final ViewRenderAdapter adapter;

  private final ViewContext context;

  private final boolean endlessRow;

  private ViewPositionBean position;

  private BeatPosition beatPosition;

  private double pageWidth;

  private double pageHeight;

  private boolean sectionProgressed;

  private StaveSystem staveSystem;

  private ViewStavesLayout stavesLayout;

  private ViewStaveSystemRenderer systemRenderer;

  private StaveVoice currentVoice;

  /**
   * The constructor.
   *
   * @param adapter the {@link ViewRenderAdapter}.
   * @param context the {@link ViewContext}.
   * @param endlessRow - {@code true} to render entire {@link io.github.musicdoc.score.Score} as a single row,
   *        {@code false} otherwise.
   */
  public ViewRendererIntern(ViewRenderAdapter adapter, ViewContext context, boolean endlessRow) {

    super();
    this.adapter = adapter;
    this.context = context;
    this.endlessRow = endlessRow;
    this.position = new ViewPositionBean();
    this.beatPosition = new BeatPosition();
    this.pageWidth = this.adapter.getPageWidth();
    this.pageHeight = this.adapter.getPageHeight();
    this.stavesLayout = new ViewStavesLayout();
  }

  @Override
  public ViewContext getContext() {

    return this.context;
  }

  ViewPage renderPages(Song song, ViewDocument document, ViewRow row) {

    Score score = song.Score().get();
    assert ((row != null) == this.endlessRow);
    int pageNumber = 1;
    double contentY = 0;
    ViewPage page = null;
    ViewBlock header = null;
    if (row == null) {
      page = new ViewPage(pageNumber);
      header = renderPageHeader(song, page);
      page.setHeader(header);
    }

    StaveSystem system = score.getStaveSystem();
    for (ScoreSection section : score.getSections()) {
      this.staveSystem = section.getStaveSystem();
      if (this.staveSystem == null) {
        this.staveSystem = system;
      }
      ScoreSectionName sectionName = section.getName();
      for (ScoreRow scoreRow : section.getRows()) {
        ScoreRowReader rowReader = new ScoreRowReader(scoreRow, this.beatPosition, this.context);
        do {
          ViewRow viewRow;
          if (row == null) {
            viewRow = new ViewRow();
          } else {
            viewRow = row;
          }
          renderRow(rowReader, viewRow, sectionName);
          sectionName = null;
          if (page != null) {
            page.getRows().add(viewRow);
          }
        } while (!rowReader.isDone());
      }
      if ((document != null) && (page != null)) {
        double y = this.position.getY();
        double heightLeftRatio = (this.pageHeight - y) / this.pageHeight;
        if (heightLeftRatio < 0.1) { // TODO should not be static 10% but rather adapt to height of row?!
          ViewBlock footer = renderPageFooter(pageNumber);
          page.setFooter(footer);
          document.getPages().add(page);
          pageNumber++;
          page = new ViewPage(pageNumber);
          this.position.set(0, contentY);
          if (pageNumber == 2) {
            header = renderPageHeader(song, page);
            contentY = this.position.getY();
          }
          page.setHeader(header);
        }
      }
      this.stavesLayout.resetRow();
    }
    return page;
  }

  private ViewBlock renderPageHeader(Song song, ViewPage page) {

    ViewBlock header = new ViewBlock();
    if (page.getNumber() == 1) {
      header.addItem(
          renderText(song.Title().get(), ViewTextType.TITLE, ViewTextAlignment.CENTER, ViewTextProgression.NEWLINE));
      String subtitle = song.Artist().get().getTarget().Title().getSafe();
      // TODO song.capo.getValue();
      header
          .addItem(renderText(subtitle, ViewTextType.SUB_TITLE, ViewTextAlignment.CENTER, ViewTextProgression.NEWLINE));
    } else {
      String text = song.Title().get();
      String artist = song.Artist().get().getTarget().Title().getSafe();
      if (!artist.isBlank()) {
        text = text + " (" + artist + ")";
      }
      Integer capo = song.Capo().get();
      if (capo != null) {
        text = text + "[" + capo + "]";
      }
      header.addItem(renderText(text, ViewTextType.SUB_TITLE, ViewTextAlignment.CENTER, ViewTextProgression.NEWLINE));
    }
    return header;
  }

  private void renderRow(ScoreRowReader rowReader, ViewRow viewRow, ScoreSectionName sectionName) {

    renderSection(sectionName, viewRow);
    if (this.systemRenderer == null) {
      this.systemRenderer = new ViewStaveSystemRenderer(this.staveSystem, rowReader, this);
    } else {
      this.systemRenderer = this.systemRenderer.getNext(this.staveSystem, rowReader);
    }
    // score lines are first rendered with positions relative to stave baseline with horizontal alignment of vertical
    // ViewColumns across multiple voices and staves
    this.position.setX(this.systemRenderer.getViewColumnStartX());
    boolean done;
    do {
      done = renderColumn(rowReader, viewRow);
    } while (!done);
    // once we have reached the end of the row we transfer relative to absolute positions.
    // then the stave system is rendered absolute and score lines are moved to their absolute position
    layoutRow(rowReader, viewRow);
  }

  private void renderSection(ScoreSectionName sectionName, ViewRow viewRow) {

    if (sectionName == null) {
      return;
    }
    ViewTextProgression progression = ViewTextProgression.V_STEP;
    if (this.endlessRow) {
      if (this.sectionProgressed) {
        progression = ViewTextProgression.NONE;
      } else {
        this.sectionProgressed = true;
      }
    }
    // TODO i18n
    String name = sectionName.getName();
    viewRow.getBlock().addItem(renderText(name, ViewTextType.SECTION, ViewTextAlignment.LEFT, progression));
  }

  private boolean renderColumn(ScoreRowReader rowReader, ViewRow viewRow) {

    boolean done = false;
    int lineCount = rowReader.getLineCount();
    ViewColumn column = new ViewColumn();
    for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
      ScoreLineReader lineReader = rowReader.getLine(lineIndex);
      this.currentVoice = lineReader.getLine().getVoice();
      BeatPosition lineBeatPosition = lineReader.getPosition();
      if (this.beatPosition.isEqualTo(lineBeatPosition)) {
        ScoreCell scoreCell = lineReader.getCell();
        if (scoreCell != null) {
          ViewCell viewCell = new ViewCell(scoreCell, this.currentVoice);
          renderCell(scoreCell, viewCell, this.currentVoice, lineReader);
          if (scoreCell.getLineBreak() == ScoreLineBreak.BREAK) {
            done = true;
          }
          column.addCell(viewCell);
        }
      }
    }
    double x = this.position.getX() + this.stavesLayout.getColumnWidth();
    if (this.endlessRow || (x <= this.pageWidth)) {
      viewRow.addColumn(column);
      this.position.setX(x);
      updateBeatPosition(rowReader, lineCount);
    } else {
      done = true;
    }
    this.stavesLayout.resetColumn();
    return done;
  }

  private void updateBeatPosition(ScoreRowReader rowReader, int lineCount) {

    BeatPosition nextBeatPosition = null;
    for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
      ScoreLineReader lineReader = rowReader.getLine(lineIndex);
      BeatPosition lineBeatPosition = lineReader.getPosition();
      if (this.beatPosition.compareTo(lineBeatPosition) == 0) {
        lineReader.getNext();
        lineBeatPosition = lineReader.getPosition();
        if ((nextBeatPosition == null) || lineBeatPosition.isBefore(nextBeatPosition)) {
          nextBeatPosition = lineBeatPosition;
        }
      }
    }
    this.beatPosition = nextBeatPosition;
  }

  private void renderCell(ScoreCell scoreCell, ViewCell viewCell, StaveVoice voice, ScoreLineReader lineReader) {

    renderChord(scoreCell.getChordContainer(), viewCell, voice, lineReader);
    renderItem(scoreCell.getItem(), viewCell, voice, lineReader);
    renderLyric(scoreCell.getLyric(), viewCell, lineReader);
  }

  private void renderChord(ChordContainer chordContainer, ViewCell viewCell, StaveVoice voice,
      ScoreLineReader lineReader) {

    if (chordContainer == null) {
      return;
    }
    ViewItemText viewChord = renderText(chordContainer.toString(), ViewTextType.CHORD_SYMBOL);
    this.stavesLayout.visit(viewChord);
    viewCell.addItem(viewChord);
  }

  private void renderItem(ValuedItem<?> item, ViewCell viewCell, StaveVoice voice, ScoreLineReader lineReader) {

    if (item == null) {
      return;
    }
    ViewItemGroup viewItem = new ViewItemGroup();
    if (item instanceof Rest) {
      renderRest((Rest) item, viewCell, voice, lineReader, viewItem);
    } else {
      renderNote((Note) item, viewCell, voice, lineReader, viewItem);
    }
    renderDecorations(item, viewCell, viewItem);
  }

  private void renderDecorations(DecoratedItem item, ViewCell viewCell, ViewItemContainer viewItem) {

    int count = item.getDecorationCount();
    for (int i = 0; i < count; i++) {
      MusicalDecoration decoration = item.getDecoration(i);
      renderDecoration(decoration, viewCell, viewItem);
    }
  }

  private void renderDecoration(MusicalDecoration decoration, ViewCell viewCell, ViewItemContainer viewItem) {

    PeriodType period = decoration.getPeriod();
    if ((period == null) || (decoration instanceof PedalDecoration)) {
      String glyphs = decoration.getGlyphs(this.context.getGlyphsContext());
      MusicalDecorationPosition pos = decoration.getPosition();
      switch (pos) {
        case ABOVE:
        case BELOW:
        case STEM:
        case NOTEHEAD:
        case LEFT:
        case RIGHT:
        case TOP:
        case BOTTOM:
      }
    } else {

    }
  }

  private void renderRest(Rest rest, ViewCell viewCell, StaveVoice voice, ScoreLineReader lineReader,
      ViewItemGroup itemGroup) {

    ViewItemText viewRest = renderText(rest, ViewTextType.MUSIC_CONTENT);
    viewCell.addItem(viewRest);
    this.stavesLayout.visit(viewRest);
    itemGroup.addItem(viewRest);
  }

  private void renderNote(Note note, ViewCell viewCell, StaveVoice voice, ScoreLineReader lineReader,
      ViewItemGroup itemGroup) {

    Stave stave = lineReader.getStave();
    Clef clef = stave.getClef();
    ClefSymbol clefSymbol = clef.getSymbol();
    // TODO stem direction, beams, etc.
    StemDirection stemDirection = voice.getStemDirection();
    String glyphs = null;
    int toneCount = note.getToneCount();
    for (int toneIndex = 0; toneIndex <= toneCount; toneIndex++) {
      NoteTone noteTone = note.getNoteTone(toneIndex);
      ViewItemText viewNote;
      int step;
      if (clefSymbol == ClefSymbol.TAB) {
        TabTone tab = noteTone.getTab();
        assert (tab != null);
        int fret = tab.getFret();
        viewNote = renderText("" + fret, ViewTextType.TAB);
        int string = tab.getString();
        int stringCount = stave.getLines();
        StringTuning tuning = clef.getTuning();
        if (tuning != null) {
          stringCount = tuning.getStringCount();
        }
        step = 2 * (stringCount - string - 1);
      } else {
        // TODO percussion clef???
        if (glyphs == null) {
          glyphs = note.getGlyphs(this.context.getGlyphsContext());
        }
        viewNote = renderText(glyphs, ViewTextType.MUSIC_CONTENT);
        Tone tone = noteTone.getTone();
        ChromaticInterval interval = clef.getLowerTone().getInterval(tone);
        step = interval.getChromaticSteps(null);
      }
      itemGroup.addItem(viewNote);
      renderDecorations(noteTone, viewCell, viewNote);
      double yOffset = this.adapter.getStepOffset(step, this.context);
      viewNote.addY(yOffset);
      viewCell.addItem(viewNote);
      this.stavesLayout.visit(viewNote);
    }
  }

  private ViewItemText renderLyric(String lyric, ViewCell viewCell, ScoreLineReader lineReader) {

    if ((lyric == null) || (lyric.isBlank())) {
      return null;
    }
    ViewItemText viewLyrics = renderText(lyric, ViewTextType.LYRICS);
    viewCell.addItem(viewLyrics);
    this.stavesLayout.visit(viewLyrics);
    return viewLyrics;
  }

  private void layoutRow(ScoreRowReader rowReader, ViewRow viewRow) {

    // TODO Auto-generated method stub

  }

  private ViewBlock renderPageFooter(int pageNumber) {

    ViewBlock footer = new ViewBlock();
    footer.addItem(renderText(Integer.toString(pageNumber), ViewTextType.FOOTER, ViewTextAlignment.RIGHT));
    return footer;
  }

  @Override
  public ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment) {

    StaveVoice voice = null;
    if (textType.asPlacementType() != null) {
      voice = this.currentVoice;
    }
    return renderText(text, textType, alignment, textType.asTextProgression(), voice);
  }

  @Override
  public ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment,
      ViewTextProgression progression, StaveVoice voice) {

    ViewItemText textItem = new ViewItemText(voice, text, textType);
    ViewPositionBean topLeft = textItem.getTopLeft();
    ViewPositionBean drawStart = textItem.getDrawStart();
    ViewPositionBean bottomRight = textItem.getBottomRight();
    ViewRectangle box = this.adapter.getTextSize(text, textType, this.context);
    double x = 0;
    double y = 0;
    if (progression != ViewTextProgression.ABSOLUTE) {
      x = this.position.getX();
      y = this.position.getY();
    }
    if (alignment == ViewTextAlignment.RIGHT) {
      assert (progression == ViewTextProgression.ABSOLUTE);
      x = this.pageWidth - box.getWidth();
      if (x < 0) {
        x = 0;
      }
    }
    double x0 = box.getX0();
    if (x0 < 0) {
      // left tilt
      drawStart.setX(x);
      x = x + x0;
    } else {
      drawStart.setX(x + x0);
    }
    topLeft.set(x, y);
    double y0 = box.getY0();
    assert (y0 >= 0); // baseline
    drawStart.setY(y + y0);
    bottomRight.set(x + box.getWidth(), y + box.getHeight());
    if (alignment == ViewTextAlignment.CENTER) {
      double widthLeft = this.pageWidth - x;
      if (widthLeft < 0) {
        // TODO
        widthLeft = 0;
      }
      drawStart.addX(widthLeft / 2);
      bottomRight.addX(widthLeft);
    }

    if (progression.isHorizontal()) {
      this.position.addX(textItem.getWidth());
    }
    if (progression.isVertical()) {
      double height = textItem.getHeight();
      double vspace = this.context.getScale() * height / 20;
      this.position.addY(height + vspace);
    }
    return textItem;
  }

}
