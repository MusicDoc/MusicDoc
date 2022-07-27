package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.tone.TonePitchMapper;

/**
 * {@link AbstractMapper Mapper} for {@link ChordContainer}.
 */
public abstract class ChordContainerMapper extends AbstractMapper<ChordContainer> {

  private static final ListCharFilter START_FILTER = TonePitchMapper.FILTER_TONE_START.joinChars(ListCharFilter.NEWLINE).join(CHORD_START,
      CHORD_END, ITEM_START, ITEM_END);

  @Override
  public ChordContainer read(MusicInputStream in, SongFormatContext context) {

    Chord chord = getChordMapper().read(in, context);
    String suffix = in.readUntil(START_FILTER, true);
    if (suffix.isEmpty() && (chord == null)) {
      return null;
    }
    return new ChordContainer(chord, suffix);
  }

  @Override
  public void write(ChordContainer chordContainer, MusicOutputStream out, SongFormatContext context) {

    out.write(chordContainer.toString());
  }
}
