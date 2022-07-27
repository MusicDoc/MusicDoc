package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link ChordContainerMapper} for {@link SongFormatAbc}.
 */
public class ChordContainerMapperAbc extends ChordContainerMapper {

  /** The singleton instance. */
  public static final ChordContainerMapperAbc INSTANCE = new ChordContainerMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public ChordContainer read(MusicInputStream in, SongFormatContext context) {

    ChordContainer result = null;
    if (in.expect('"')) {
      result = super.read(in, context);
      if (result == null) {
        result = ChordContainer.EMPTY;
        in.expect(CHORD_END, true);
      } else {
        ChordContainer current = result;
        while (!in.expect('"')) {
          ChordContainer next = super.read(in, context);
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
  public void write(ChordContainer chordContainer, MusicOutputStream out, SongFormatContext context) {

    while (chordContainer != null) {
      out.write(CHORD_START);
      super.write(chordContainer, out, context);
      out.write(CHORD_END);
      chordContainer = chordContainer.getNext();
    }
  }

}
