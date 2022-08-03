package io.github.musicdoc.volta;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link VoltaMapper} for {@link SongFormatOpenSong}.
 */
public class VoltaMapperOpenSong extends VoltaMapper {

  /** The singleton instance. */
  public static final VoltaMapperOpenSong INSTANCE = new VoltaMapperOpenSong();

  /**
   * The constructor.
   */
  protected VoltaMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
