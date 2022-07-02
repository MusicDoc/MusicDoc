package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link TonePitchMapper} for {@link SongFormatOpenSong}.
 */
public class TonePitchMapperOpenSong extends TonePitchMapper {

  /** The singleton instance. */
  public static final TonePitchMapperOpenSong INSTANCE = new TonePitchMapperOpenSong();

  /**
   * The constructor.
   */
  protected TonePitchMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
