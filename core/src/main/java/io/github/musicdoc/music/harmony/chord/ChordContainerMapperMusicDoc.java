package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.format.SongFormatOptions;

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
  public ChordContainer parse(MusicInputStream chars, SongFormatOptions options) {

    ChordContainer result = null;
    if (chars.expect(CHORD_START)) {
      result = super.parse(chars, options);
      if (result == null) {
        result = ChordContainer.EMPTY;
        chars.expect(CHORD_END, true);
      } else {
        ChordContainer current = result;
        while (!chars.expect(CHORD_END)) {
          ChordContainer next = super.parse(chars, options);
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
  public void format(ChordContainer chordContainer, MusicOutputStream out, SongFormatOptions options) {

    while (chordContainer != null) {
      out.append(CHORD_START);
      super.format(chordContainer, out, options);
      out.append(CHORD_END);
      chordContainer = chordContainer.getNext();
    }
  }

  @Override
  protected ChordMapper getChordMapper() {

    return ChordMapperMusicDoc.INSTANCE;
  }

}
