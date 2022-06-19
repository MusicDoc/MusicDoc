package io.github.musicdoc.music.harmony.chord;

import java.io.IOException;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link ChordContainer}.
 */
public class ChordContainerMapper extends AbstractMapper<ChordContainer> {

  /** The singleton instance. */
  public static final ChordContainerMapper INSTANCE = new ChordContainerMapper();

  private static final ListCharFilter START_FILTER = TonePitchMapper.FILTER_TONE_START.joinChars(ListCharFilter.NEWLINE)
      .join(CHORD_START, CHORD_END, ITEM_START, ITEM_END);

  @Override
  public ChordContainer parse(CharStream chars) {

    Chord chord = ChordMapper.INSTANCE.parse(chars);
    String suffix = chars.readUntil(START_FILTER, true);
    if (suffix.isEmpty() && (chord == null)) {
      return null;
    }
    return new ChordContainer(chord, suffix);
  }

  @Override
  public void format(ChordContainer chordContainer, Appendable buffer, MusicFormatOptions options) throws IOException {

    buffer.append(chordContainer.toString());
  }
}
