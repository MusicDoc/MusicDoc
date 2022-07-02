package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.tone.TonePitchMapper;

/**
 * {@link AbstractMapper Mapper} for {@link ChordContainer}.
 */
public abstract class ChordContainerMapper extends AbstractMapper<ChordContainer> {

  private static final ListCharFilter START_FILTER = TonePitchMapper.FILTER_TONE_START.joinChars(ListCharFilter.NEWLINE)
      .join(CHORD_START, CHORD_END, ITEM_START, ITEM_END);

  @Override
  public ChordContainer parse(MusicInputStream chars, SongFormatOptions options) {

    Chord chord = getChordMapper().parse(chars, options);
    String suffix = chars.readUntil(START_FILTER, true);
    if (suffix.isEmpty() && (chord == null)) {
      return null;
    }
    return new ChordContainer(chord, suffix);
  }

  /**
   * @return the {@link ChordMapper}.
   */
  protected abstract ChordMapper getChordMapper();

  @Override
  public void format(ChordContainer chordContainer, MusicOutputStream out, SongFormatOptions options) {

    out.append(chordContainer.toString());
  }
}
