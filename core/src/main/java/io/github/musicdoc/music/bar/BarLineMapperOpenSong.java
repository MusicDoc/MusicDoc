package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

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
