package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

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
