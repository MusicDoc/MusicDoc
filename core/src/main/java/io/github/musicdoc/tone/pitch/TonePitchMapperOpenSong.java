package io.github.musicdoc.tone.pitch;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link TonePitchMapper} for {@link SongFormatOpenSong}.
 */
public class TonePitchMapperOpenSong extends TonePitchMapperBase {

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
