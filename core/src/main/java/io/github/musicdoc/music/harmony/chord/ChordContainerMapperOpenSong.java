package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ChordContainerMapper} for {@link SongFormatOpenSong}.
 */
public class ChordContainerMapperOpenSong extends ChordContainerMapper {

  /** The singleton instance. */
  public static final ChordContainerMapperOpenSong INSTANCE = new ChordContainerMapperOpenSong();

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  protected ChordMapper getChordMapper() {

    return ChordMapperOpenSong.INSTANCE;
  }

}
