package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link BarLineMapper} for {@link SongFormatOpenSong}.
 */
public class BarLineMapperOpenSong extends BarLineMapper {

  /** The singleton instance. */
  public static final BarLineMapperOpenSong INSTANCE = new BarLineMapperOpenSong();

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
