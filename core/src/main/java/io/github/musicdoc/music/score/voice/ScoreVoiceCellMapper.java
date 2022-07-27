package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.stave.StaveChange;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreVoiceCell}.
 */
public abstract class ScoreVoiceCellMapper extends AbstractMapper<ScoreVoiceCell> {

  static final CharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(CHORD_START, ITEM_START, BAR_SINGLE, BAR_REPEAT, BAR_THICK_2);

  @Override
  public ScoreVoiceCell read(MusicInputStream in, SongFormatContext context) {

    StaveChange staveChange = getStaveChangeMapper().read(in, context);
    ValuedItem<?> item = getValuedItemMapper().read(in, context);
    ChordContainer chordContainer = getChordContainerMapper().read(in, context);
    if ((item == null) && (chordContainer != null)) {
      item = getValuedItemMapper().read(in, context);
    }
    String lyric = in.readUntil(STOP_FILTER, true);
    BarLine bar = getBarLineMapper().read(in, context);
    if ((staveChange == null) && (item == null) && (chordContainer == null) && lyric.isEmpty() && (bar == null)) {
      return null;
    }
    ScoreVoiceCell currentCell = new ScoreVoiceCell();
    currentCell.setStaveChange(staveChange);
    currentCell.setChordContainer(chordContainer);
    currentCell.setItem(item);
    currentCell.setLyric(lyric);
    currentCell.setBar(bar);
    return currentCell;
  }

  @Override
  public void write(ScoreVoiceCell cell, MusicOutputStream out, SongFormatContext context) {

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
