package io.github.musicdoc.view.render;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.decoration.MusicalDecorationPosition;
import io.github.musicdoc.decoration.PedalDecoration;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.note.NoteTone;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.rest.Rest;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLineBreak;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.score.section.ScoreSectionName;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveBracket;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.layout.ViewLayout;
import io.github.musicdoc.view.layout.ViewPlacement;
import io.github.musicdoc.view.layout.ViewPlacementType;
import io.github.musicdoc.view.layout.ViewStaveLayout;
import io.github.musicdoc.view.layout.ViewTextAlignment;
import io.github.musicdoc.view.layout.ViewTextProgression;
import io.github.musicdoc.view.layout.ViewTextType;
import io.github.musicdoc.view.model.ViewBlock;
import io.github.musicdoc.view.model.ViewCell;
import io.github.musicdoc.view.model.ViewColumn;
import io.github.musicdoc.view.model.ViewDocument;
import io.github.musicdoc.view.model.ViewItemText;
import io.github.musicdoc.view.model.ViewPage;
import io.github.musicdoc.view.model.ViewRow;
import io.github.musicdoc.view.reader.BeatPosition;
import io.github.musicdoc.view.reader.ScoreLineReader;
import io.github.musicdoc.view.reader.ScoreRowReader;

/**
 * Internal stateful implementation of {@link ViewRenderer} logic.
 */
class ViewRendererStateful {

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

  private int staveIndex;

  private ViewLayout layout;

  private boolean firstSystemRendered;

  private MusicalGlyphsContext glyphsContext;

  /**
   * The constructor.
   *
   * @param adapter the {@link ViewRenderAdapter}.
   * @param context the {@link ViewContext}.
   */
  public ViewRendererStateful(ViewRenderAdapter adapter, ViewContext context, boolean endlessRow) {

    super();
    this.adapter = adapter;
    this.context = context;
    this.endlessRow = endlessRow;
    this.position = new ViewPositionBean();
    this.beatPosition = new BeatPosition();
    this.pageWidth = this.adapter.getPageWidth();
    this.pageHeight = this.adapter.getPageHeight();
    this.firstSystemRendered = false;
    this.layout = new ViewLayout();
    this.glyphsContext = new MusicalGlyphsContext();
  }

  ViewPage renderPages(Song song, ViewDocument document, ViewRow row) {

    Score score = song.score.getValue();
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
        boolean done = false;
        while (!done) {
          ViewRow viewRow;
          boolean endless = false;
          if (row == null) {
            viewRow = new ViewRow();
          } else {
            viewRow = row;
            endless = true;
          }
          done = renderRow(rowReader, viewRow, sectionName, endless);
          if (page != null) {
            page.getRows().add(viewRow);
          }
        }
        sectionName = null;
      }
      if ((document != null) && (page != null)) {
        double y = this.position.getY();
        double heightLeftRatio = (this.pageHeight - y) / this.pageHeight;
        if (heightLeftRatio < 0.1) { // TODO should not be static 10% but rather adapt to height of row?!
          ViewBlock footer = renderPageFooter(this.context, pageNumber);
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
      this.layout.resetRow();
    }
    return page;
  }

  private ViewBlock renderPageHeader(Song song, ViewPage page) {

    ViewBlock header = new ViewBlock();
    if (page.getNumber() == 1) {
      header.getItems().add(renderText(song.title.getValue(), ViewTextType.TITLE, ViewPlacement.ABSOLUTE,
          ViewTextAlignment.CENTER, ViewTextProgression.NEWLINE));
      String subtitle = song.artist.getValueOrDefault();
      // TODO song.capo.getValue();
      header.getItems().add(renderText(subtitle, ViewTextType.SUB_TITLE, ViewPlacement.ABSOLUTE,
          ViewTextAlignment.CENTER, ViewTextProgression.NEWLINE));
    } else {
      String text = song.title.getValue();
      String artist = song.artist.getValueOrDefault();
      if (!artist.isBlank()) {
        text = text + " (" + artist + ")";
      }
      Integer capo = song.capo.getValue();
      if (capo != null) {
        text = text + "[" + capo + "]";
      }
      header.getItems().add(renderText(text, ViewTextType.SUB_TITLE, ViewPlacement.ABSOLUTE, ViewTextAlignment.CENTER,
          ViewTextProgression.NEWLINE));
    }
    return header;
  }

  private boolean renderRow(ScoreRowReader rowReader, ViewRow viewRow, ScoreSectionName sectionName, boolean endless) {

    if (sectionName != null) {
      ViewTextProgression progression = ViewTextProgression.V_STEP;
      if (endless) {
        if (this.sectionProgressed) {
          progression = ViewTextProgression.NONE;
        } else {
          this.sectionProgressed = true;
        }
      }
      // TODO i18n
      String name = sectionName.getName();
      viewRow.getBlock().getItems()
          .add(renderText(name, ViewTextType.SECTION, ViewPlacement.ABSOLUTE, ViewTextAlignment.LEFT, progression));
    }
    // score lines a first rendered with positions relative to stave baseline with horizontal alignment of vertical
    // ViewColumns accross multiple voices and staves
    // TODO we first need to render part of the stave system to know the left position were we can start the staves...
    boolean done = renderScoreLines(rowReader, viewRow);
    if (done) {
      // once we have reached the end of the row we transfer relative to absolute positions.
      // then the stave system is rendered absolute and score lines are moved to their absolute position
      renderStaveSystem(rowReader, viewRow);
    }
    return done;
  }

  private boolean renderScoreLines(ScoreRowReader rowReader, ViewRow viewRow) {

    boolean done = false;
    int lineCount = rowReader.getLineCount();
    this.staveIndex = 0;
    Stave stave = null;
    ViewColumn column = new ViewColumn();
    for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
      ScoreLineReader lineReader = rowReader.getLine(lineIndex);
      Stave currentStave = lineReader.getLine().getVoice().getStave();
      if ((stave != currentStave) && (stave != null)) {
        this.staveIndex++;
      }
      stave = currentStave;
      BeatPosition lineBeatPosition = lineReader.getPosition();
      if (this.beatPosition.isEqualTo(lineBeatPosition)) {
        ScoreCell scoreCell = lineReader.getCell();
        if (scoreCell != null) {
          ViewCell viewCell = new ViewCell(scoreCell);
          renderCell(scoreCell, viewCell);
          if (scoreCell.getLineBreak() == ScoreLineBreak.BREAK) {
            done = true;
          }
          column.addCell(viewCell);
        }
      }
    }
    double x = this.position.getX() + this.layout.getColumnWidth();
    if (this.endlessRow || (x <= this.pageWidth)) {
      viewRow.addColumn(column);
      this.position.setX(x);
      updateBeatPosition(rowReader, lineCount);
    } else {
      done = true;
    }
    this.layout.resetColumn();
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

  private void renderCell(ScoreCell scoreCell, ViewCell viewCell) {

    renderChord(scoreCell.getChordContainer(), viewCell);
    renderItem(scoreCell.getItem(), viewCell);
    renderLyric(scoreCell.getLyric(), viewCell);
  }

  private void renderChord(ChordContainer chordContainer, ViewCell cell) {

    if (chordContainer == null) {
      return;
    }
    ViewPlacement placement = new ViewPlacement(this.staveIndex, ViewPlacementType.CHORD);
    ViewItemText viewChord = renderText(chordContainer.toString(), ViewTextType.CHORD_SYMBOL, placement);
    this.layout.visit(viewChord);
    cell.addItem(viewChord);
  }

  private void renderItem(ValuedItem<?> item, ViewCell cell) {

    if (item == null) {
      return;
    }
    ViewItemText viewItem;
    if (item instanceof Rest) {
      viewItem = renderRest((Rest) item, cell);
    } else {
      viewItem = renderNote((Note) item, cell);
    }
    for (MusicalDecoration decoration : item.getDecorations()) {
      renderDecoration(decoration, cell, viewItem);
    }
  }

  private void renderDecoration(MusicalDecoration decoration, ViewCell cell, ViewItemText viewItem) {

    PeriodType period = decoration.getPeriod();
    if ((period == null) || (decoration instanceof PedalDecoration)) {
      String glyphs = decoration.getGlyphs(this.glyphsContext);
      MusicalDecorationPosition pos = decoration.getPosition();

    } else {

    }
  }

  private ViewItemText renderRest(Rest rest, ViewCell cell) {

    String glyphs = rest.getGlyphs(this.glyphsContext);
    ViewPlacement placement = new ViewPlacement(this.staveIndex, ViewPlacementType.STAVE);
    ViewItemText viewRest = renderText(glyphs, ViewTextType.MUSIC, placement);
    cell.addItem(viewRest);
    this.layout.visit(viewRest);
    return viewRest;
  }

  private ViewItemText renderNote(Note note, ViewCell cell) {

    // TODO stem direction, beams, etc.
    String glyphs = note.getGlyphs(this.glyphsContext);
    ViewPlacement placement = new ViewPlacement(this.staveIndex, ViewPlacementType.STAVE);
    int toneCount = note.getToneCount();
    ViewItemText viewNote = null;
    for (int toneIndex = 0; toneIndex <= toneCount; toneIndex++) {
      viewNote = renderText(glyphs, ViewTextType.MUSIC, placement);
      NoteTone noteTone = note.getNoteTone(toneIndex);
      Tone tone = noteTone.getTone();
      // TODO vertical alignment
      cell.addItem(viewNote);
      this.layout.visit(viewNote);
    }
    return viewNote;
  }

  private void renderLyric(String lyric, ViewCell viewCell) {

    // TODO Auto-generated method stub

  }

  // also does the row layout by transforming from relative to absolute positions.
  private void renderStaveSystem(ScoreRowReader rowReader, ViewRow viewRow) {

    renderStaveSystemRecursive(this.staveSystem, this.layout.getStaveLayout(), rowReader, viewRow);
    this.firstSystemRendered = true;

    int lineCount = rowReader.getLineCount();
    for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
      ViewColumn column = new ViewColumn();
      // ViewCell cell;
      // cell.set
      // column.addCell(cell);
      // if ()
      ScoreLineReader lineReader = rowReader.getLine(lineIndex);
      lineReader.getLine().getVoice();
      ScoreCell cell = lineReader.getCell();
      if (cell != null) {
      }
      viewRow.addColumn(column);
    }
  }

  private boolean renderStaveSystemRecursive(StaveSystem system, ViewStaveLayout staveLayout, ScoreRowReader rowReader,
      ViewRow viewRow) {

    boolean visible;
    if (system.isSingle()) {
      Stave stave = system.getStave();
      visible = this.context.getStaveAcitvity().isActive(stave);
      if (visible) {
        for (StaveVoice voice : stave.getVoices()) {

        }
      }
    } else {
      visible = false;
      ViewStaveLayout childLayout = staveLayout.getOrCreateNext();
      for (StaveSystem child : system.getChildren()) {
        boolean childVisible = renderStaveSystemRecursive(child, childLayout, rowReader, viewRow);
        if (childVisible) {
          visible = true;
        }
      }
    }
    if (visible) {
      StaveBracket bracket = system.getBracket();
      if (bracket != null) {
        String glyphs = bracket.getGlyphs(this.glyphsContext);
        ViewItemText viewBracket = renderText(glyphs, ViewTextType.MUSIC);
        viewRow.getBlock().addItem(viewBracket);
        staveLayout.setWidthMax(viewBracket.getWidth());
      }
    }
    return visible;
  }

  private ViewBlock renderPageFooter(ViewContext context, int pageNumber) {

    ViewBlock footer = new ViewBlock();
    footer.addItem(renderText(Integer.toString(pageNumber), ViewTextType.FOOTER, ViewTextAlignment.RIGHT));
    return footer;
  }

  private ViewItemText renderText(String text, ViewTextType textType) {

    return renderText(text, textType, ViewTextAlignment.LEFT);
  }

  private ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment) {

    return renderText(text, textType, ViewPlacement.ABSOLUTE, alignment, ViewTextProgression.ABSOLUTE);
  }

  private ViewItemText renderText(String text, ViewTextType textType, ViewPlacement placement) {

    return renderText(text, textType, placement, ViewTextAlignment.LEFT, ViewTextProgression.ABSOLUTE);
  }

  private ViewItemText renderText(String text, ViewTextType textType, ViewPlacement placement,
      ViewTextAlignment alignment, ViewTextProgression progression) {

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
    ViewPositionBean topLeft = new ViewPositionBean(x, y);
    ViewPositionBean draw = new ViewPositionBean();
    double x0 = box.getX0();
    if (x0 < 0) {
      // left tilt
      draw.setX(x);
      x = x + x0; //
      topLeft.setX(x);
    } else {
      draw.setX(x + x0);
    }
    double y0 = box.getY0();
    assert (y0 >= 0); // baseline
    draw.setY(y + y0);
    ViewPositionBean bottomRight = new ViewPositionBean(x + box.getWidth(), y + box.getHeight());
    if (alignment == ViewTextAlignment.CENTER) {
      double widthLeft = this.pageWidth - x;
      if (widthLeft < 0) {
        // TODO
        widthLeft = 0;
      }
      draw.addX(widthLeft / 2);
      bottomRight.addX(widthLeft);
    }

    ViewItemText titleItem = new ViewItemText(placement, topLeft, bottomRight, draw, text, textType);
    if (progression.isHorizontal()) {
      this.position.addX(titleItem.getWidth());
    }
    if (progression.isVertical()) {
      double height = titleItem.getHeight();
      double vspace = this.context.getScale() * height / 20;
      this.position.addY(height + vspace);
    }
    return titleItem;
  }

}
