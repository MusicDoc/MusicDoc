package io.github.musicdoc.score.cell;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rythm.value.ValuedItem;
import io.github.musicdoc.stave.StaveChange;

/**
 * Basic implementation of {@link ScoreCellMapper}.
 */
public abstract class ScoreCellMapperBase extends ScoreCellMapper {

  @Override
  public ScoreCell read(MusicInputStream in, SongFormatContext context) {

    StaveChange staveChange = getStaveChangeMapper().read(in, context);
    ValuedItem<?> item = getValuedItemMapper().read(in, context);
    ChordContainer chordContainer = getChordContainerMapper().read(in, context);
    if ((item == null) && (chordContainer != null)) {
      item = getValuedItemMapper().read(in, context);
    }
    String lyric = readLyric(in, context);
    BarLine bar = getBarLineMapper().read(in, context);
    if ((staveChange == null) && (item == null) && (chordContainer == null) && lyric.isEmpty() && (bar == null)) {
      return null;
    }
    ScoreCell cell = new ScoreCell();
    cell.setStaveChange(staveChange);
    cell.setChordContainer(chordContainer);
    cell.setItem(item);
    cell.setLyric(lyric);
    cell.setBar(bar);
    if (bar != null) {
      context.getTonePitchChange().clear();
    }
    return cell;
  }

  /**
   * @param in tje {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link ScoreCell#getLyric() lyric}.
   */
  protected String readLyric(MusicInputStream in, SongFormatContext context) {

    in.skipWhile(' ');
    return "";
  }

  @Override
  public void write(ScoreCell cell, MusicOutputStream out, SongFormatContext context) {

    if (cell == null) {
      return;
    }
    getStaveChangeMapper().write(cell.getStaveChange(), out, context);
    getChordContainerMapper().write(cell.getChordContainer(), out, context);
    getValuedItemMapper().write(cell.getItem(), out, context);
    out.write(cell.getLyric());
    getBarLineMapper().write(cell.getBar(), out, context);
  }

}
