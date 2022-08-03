package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ChordExtensionMapper} for {@link SongFormatOpenSong}.
 */
public class ChordExtensionMapperOpenSong extends ChordExtensionMapper {

  /** The singleton instance. */
  public static final ChordExtensionMapperOpenSong INSTANCE = new ChordExtensionMapperOpenSong();

  /**
   * The constructor.
   */
  protected ChordExtensionMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
