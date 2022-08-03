package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link BarLineTypeMapper} for {@link SongFormatOpenSong}.
 */
public class BarLineTypeMapperOpenSong extends BarLineTypeMapperBase {

  /** The singleton instance. */
  public static final BarLineTypeMapperOpenSong INSTANCE = new BarLineTypeMapperOpenSong();

  /**
   * The constructor.
   */
  protected BarLineTypeMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
