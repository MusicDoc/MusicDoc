package io.github.musicdoc.music.harmony.chord;

import java.io.IOException;

import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link ChordContainerMapper} for ChordPro format.
 */
public class ChordContainerMapperChordPro extends ChordContainerMapper {

  /** The singleton instance. */
  @SuppressWarnings("hiding")
  public static final ChordContainerMapperChordPro INSTANCE = new ChordContainerMapperChordPro();

  @Override
  public ChordContainer parse(CharStream chars) {

    ChordContainer result = null;
    if (chars.expect(CHORD_START)) {
      result = super.parse(chars);
      if (result == null) {
        result = ChordContainer.EMPTY;
        chars.expect(CHORD_END, true);
      } else {
        ChordContainer current = result;
        while (!chars.expect(CHORD_END)) {
          ChordContainer next = super.parse(chars);
          if (next != null) {
            current.setNext(next);
            current = next;
          }
        }
      }
    }
    return result;
  }

  @Override
  public void format(ChordContainer chordContainer, Appendable buffer, MusicFormatOptions options) throws IOException {

    while (chordContainer != null) {
      buffer.append(CHORD_START);
      super.format(chordContainer, buffer, options);
      buffer.append(CHORD_END);
      chordContainer = chordContainer.getNext();
    }
  }

}
