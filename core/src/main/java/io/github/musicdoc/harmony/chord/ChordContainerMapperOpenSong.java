package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ChordContainerMapper} for {@link SongFormatOpenSong}.
 */
public class ChordContainerMapperOpenSong extends ChordContainerMapperBase {

  /** The singleton instance. */
  public static final ChordContainerMapperOpenSong INSTANCE = new ChordContainerMapperOpenSong();

  /**
   * The constructor.
   */
  public ChordContainerMapperOpenSong() {

    super('\0', '\0');
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
