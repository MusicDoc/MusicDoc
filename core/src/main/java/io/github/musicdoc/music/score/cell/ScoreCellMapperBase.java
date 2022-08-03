package io.github.musicdoc.music.score.cell;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.stave.StaveChange;

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
    ScoreCell currentCell = new ScoreCell();
    currentCell.setStaveChange(staveChange);
    currentCell.setChordContainer(chordContainer);
    currentCell.setItem(item);
    currentCell.setLyric(lyric);
    currentCell.setBar(bar);
    return currentCell;
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
    getValuedItemMapper().write(cell.getItem(), out, context);
    getChordContainerMapper().write(cell.getChordContainer(), out, context);
    out.write(cell.getLyric());
    getBarLineMapper().write(cell.getBar(), out, context);
  }

}
