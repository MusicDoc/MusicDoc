package io.github.musicdoc.music.harmony;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link TonalSystemMapper} for {@link SongFormatOpenSong}.
 */
public class TonalSystemMapperOpenSong extends TonalSystemMapper {

  /** The singleton instance. */
  public static final TonalSystemMapperOpenSong INSTANCE = new TonalSystemMapperOpenSong();

  /**
   * The constructor.
   */
  protected TonalSystemMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
