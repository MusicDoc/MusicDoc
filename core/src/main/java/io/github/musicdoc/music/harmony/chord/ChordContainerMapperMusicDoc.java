package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ChordContainerMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordContainerMapperMusicDoc extends ChordContainerMapper {

  /** The singleton instance. */
  public static final ChordContainerMapperMusicDoc INSTANCE = new ChordContainerMapperMusicDoc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public ChordContainer read(MusicInputStream in, SongFormatContext context) {

    ChordContainer result = null;
    if (in.expect(CHORD_START)) {
      result = super.read(in, context);
      if (result == null) {
        result = ChordContainer.EMPTY;
        in.expect(CHORD_END, true);
      } else {
        ChordContainer current = result;
        while (!in.expect(CHORD_END)) {
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
