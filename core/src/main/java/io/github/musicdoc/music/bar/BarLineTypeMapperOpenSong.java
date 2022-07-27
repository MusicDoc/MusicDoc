package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link BarLineTypeMapper} for {@link SongFormatOpenSong}.
 */
public class BarLineTypeMapperOpenSong extends BarLineTypeMapper {

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
