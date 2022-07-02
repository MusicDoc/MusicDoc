package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveMapper;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreVoiceCell}.
 */
public abstract class ScoreVoiceCellMapper extends AbstractMapper<ScoreVoiceCell> {

  static final CharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(CHORD_START, ITEM_START, STAVE_START, BAR_SINGLE,
      BAR_REPEAT);

  @Override
  public ScoreVoiceCell parse(MusicInputStream chars, SongFormatOptions options) {

    ScoreVoiceCell currentCell = new ScoreVoiceCell();
    ScoreVoiceCell result = currentCell;
    Stave stave = getStaveMapper().parse(chars, options);
    currentCell.setStave(stave);

    ValuedItem<?> item = getValuedItemMapper().parse(chars, options);
    ChordContainer chordContainer = getChordContainerMapper().parse(chars, options);
    currentCell.setChordContainer(chordContainer);
    if ((item == null) && (chordContainer != null)) {
      item = getValuedItemMapper().parse(chars, options);
    }
    currentCell.setItem(item);
    String lyric = chars.readUntil(STOP_FILTER, true);
    currentCell.setLyric(lyric);
    BarLine bar = getBarLineMapper().parse(chars, options);
    currentCell.setBar(bar);
    return result;
  }

  @Override
  public void format(ScoreVoiceCell cell, MusicOutputStream out, SongFormatOptions options) {

    if (cell == null) {
      return;
    }
    getStaveMapper().format(cell.getStave(), out, options);
    getValuedItemMapper().format(cell.getItem(), out, options);
    getChordContainerMapper().format(cell.getChordContainer(), out, options);
    out.append(cell.getLyric());
    getBarLineMapper().format(cell.getBar(), out, options);
  }

  /**
   * @return the {@link BarLineMapperMusicDoc}.
   */
  protected abstract BarLineMapper getBarLineMapper();

  /**
   * @return the {@link StaveMapper}.
   */
  protected abstract StaveMapper getStaveMapper();

  /**
   * @return the {@link ValuedItemMapper}.
   */
  protected abstract ValuedItemMapper getValuedItemMapper();

  /**
   * @return the {@link ChordContainerMapper}.
   */
  protected abstract ChordContainerMapper getChordContainerMapper();
}
