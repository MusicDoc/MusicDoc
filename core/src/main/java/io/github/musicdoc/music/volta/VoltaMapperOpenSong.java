package io.github.musicdoc.music.volta;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

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
