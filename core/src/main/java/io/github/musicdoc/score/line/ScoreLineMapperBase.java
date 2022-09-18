package io.github.musicdoc.score.line;

import java.util.List;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.StaveChange;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;

/**
 * Abstract base implementation of {@link ScoreLineMapper}.
 */
public abstract class ScoreLineMapperBase extends ScoreLineMapper {

  /**
   * {@link ListCharFilter} {@link ListCharFilter#accept(char) accepting} the end of a
   * {@link io.github.musicdoc.score.cell.ScoreCell#getLyric() lyric segment}.
   */
  protected static final ListCharFilter LYRIC_END_FILTER = ListCharFilter.allOf(' ', '-', '_');

  private final String voiceStart;

  private final String voiceEnd;

  private final String voiceEndFormat;

  private final String commentPrefix;

  /**
   * The constructor.
   *
   * @param voiceStart the start sequence of the {@link StaveVoice#getId() voice ID}.
   * @param voiceEnd the end sequence of the {@link StaveVoice#getId() voice ID}.
   * @param commentPrefix the prefix for {@link ScoreLine#getComment() comments}.
   */
  protected ScoreLineMapperBase(String voiceStart, String voiceEnd, String commentPrefix) {

    super();
    this.voiceStart = voiceStart;
    this.voiceEnd = voiceEnd;
    if (voiceEnd.endsWith(" ")) {
      this.voiceEndFormat = voiceEnd;
    } else {
      this.voiceEndFormat = voiceEnd + " ";
    }
    this.commentPrefix = commentPrefix;
  }

  @Override
  public ScoreLine read(MusicInputStream in, SongFormatContext context) {

    if (in.expect(this.commentPrefix, false)) {
      String comment = in.readLine();
      return new ScoreCommentLine(comment);
    } else if (in.skipNewline()) {
      return ScoreEmptyLine.INSTANCE;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    String voiceId = null;
    if ((this.voiceStart != null) && in.expect(this.voiceStart, false)) {
      in.skipWhile(' '); // be tolerant
      voiceId = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
      in.skipWhile(' '); // be tolerant
      if (!in.expect(this.voiceEnd, false)) {
        in.addError("Missing voice end marker (" + this.voiceEnd + ").");
      }
    }
    StaveVoiceContainer voiceContainer = context.getStaveVoiceContainer();
    if (voiceContainer != null) {
      StaveVoice voice = voiceContainer.getVoice(voiceId);
      if (voice != null) {
        line.setVoice(voice);
        context.setStave(voice.getStave());
      }
    }
    readCells(line, in, context);
    return line;
  }

  /**
   * @param line the {@link ScoreVoiceLine} where to add the {@link ScoreCell}s when they have been read.
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void readCells(ScoreVoiceLine line, MusicInputStream in, SongFormatContext context) {

    while (in.hasNext() && !in.skipNewline()) {
      ScoreCell cell = readCell(line, in, context);
      if (cell != null) {
        line.add(cell);
      }
    }
  }

  /**
   * @param line the {@link ScoreVoiceLine} where to add the {@link ScoreCell} that has been read.
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @return the {@link ScoreCell} that has been read.
   */
  protected ScoreCell readCell(ScoreVoiceLine line, MusicInputStream in, SongFormatContext context) {

    StaveChange staveChange = getStaveChangeMapper().read(in, context);
    ValuedItem<?> item = readValuedItem(in, context);
    ChordContainer chordContainer = getChordContainerMapper().read(in, context);
    // allow reverse order for item and chord...
    if ((item == null) && (chordContainer != null)) {
      item = getValuedItemMapper().read(in, context);
    }
    String lyric = readLyric(in, context);
    BarLine bar = getBarLineMapper().read(in, context);
    if ((staveChange == null) && (item == null) && (chordContainer == null) && lyric.isEmpty() && (bar == null)) {
      in.addError("Missing voice cell - ignoring character to prevent infinity loop.");
      in.next();
      return null;
    }
    ScoreLineBreak lineBreak = getScoreLineBreakMapper().read(in, context);
    ScoreCell cell = new ScoreCell();
    cell.setStaveChange(staveChange);
    cell.setChordContainer(chordContainer);
    cell.setItem(item);
    cell.setLyric(lyric);
    cell.setBar(bar);
    cell.setLineBreak(lineBreak);
    if (bar != null) {
      context.getTonePitchChange().clear();
    }
    return cell;
  }

  private ValuedItem<?> readValuedItem(MusicInputStream in, SongFormatContext context) {

    return getValuedItemMapper().read(in, context);
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link ScoreCell#getLyric() lyric}.
   */
  protected String readLyric(MusicInputStream in, SongFormatContext context) {

    // to be overridden
    in.skipWhile(' ');
    return "";
  }

  @Override
  public void write(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    if (line == null) {
      return;
    }
    String comment = line.getComment();
    if (comment == null) {
      StaveVoice voice = line.getVoice();
      if ((voice != null) && (this.voiceStart != null)) {
        String id = voice.getId();
        if ((id != null) && !id.isEmpty()) {
          out.write(this.voiceStart);
          out.write(id);
          out.write(this.voiceEndFormat);
        }
        context.setStave(voice.getStave());
      }
      writeCells(line, out, context);
    } else if (line != ScoreEmptyLine.INSTANCE) {
      out.write(this.commentPrefix);
      out.write(comment);
      out.write(NEWLINE_CHAR);
    }
  }

  /**
   * @param line the {@link ScoreLine} to write.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writeCells(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    List<ScoreCell> cells = line.getCells();
    int size = cells.size();
    if (size == 0) {
      return;
    }
    ScoreCell previous = null;
    ScoreCell cell = cells.get(0);
    for (int i = 1; i < size; i++) {
      ScoreCell next = cells.get(i);
      if (cell != null) { // should never be null...
        writeCell(cell, previous, next, line, i, out, context);
      }
      previous = cell;
      cell = next;
    }
    writeCell(cell, previous, null, line, size - 1, out, context);
    out.write(NEWLINE_CHAR);
  }

  /**
   * @param cell the {@link ScoreCell} to write.
   * @param previous the {@link ScoreCell} from the previous call of thie method or {@code null} if this is the first
   *        cell in the current line.
   * @param next the {@link ScoreCell} that will come next or {@code null} if this is the last {@link ScoreCell}.
   * @param line the {@link ScoreLine} containing the {@link ScoreCell} to write.
   * @param cellIndexx the current index of the {@link ScoreCell} to write.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writeCell(ScoreCell cell, ScoreCell previous, ScoreCell next, ScoreLine line, int cellIndexx,
      MusicOutputStream out, SongFormatContext context) {

    writeCell(cell, cell.getStaveChange(), cell.getChordContainer(), cell.getItem(), cell.getLyric(), cell.getBar(),
        out, context);
  }

  /**
   * @param cell the {@link ScoreCell} to write.
   * @param staveChange the {@link ScoreCell#getStaveChange() stave change}.
   * @param chordContainer the {@link ScoreCell#getChordContainer() chord container}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param bar the {@link ScoreCell#getBar() bar}.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writeCell(ScoreCell cell, StaveChange staveChange, ChordContainer chordContainer, ValuedItem<?> item,
      String lyric, BarLine bar, MusicOutputStream out, SongFormatContext context) {

    getStaveChangeMapper().write(staveChange, out, context);
    getChordContainerMapper().write(chordContainer, out, context);
    getValuedItemMapper().write(item, out, context);
    writeLyric(lyric, cell, out, context);
    getBarLineMapper().write(bar, out, context);
    if (bar != null) {
      context.getTonePitchChange().clear();
    }
  }

  /**
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param cell the {@link ScoreCell} with the {@link ScoreCell#getLyric() lyric} to write.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writeLyric(String lyric, ScoreCell cell, MusicOutputStream out, SongFormatContext context) {

    out.write(lyric);
  }

}
