package io.github.musicdoc.rythm.fraction;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link PlainFractionMapper} for {@link SongFormatOpenSong}.
 */
public class PlainFractionMapperOpenSong extends PlainFractionMapper {

  /** The singleton instance. */
  public static final PlainFractionMapperOpenSong INSTANCE = new PlainFractionMapperOpenSong();

  /**
   * The constructor.
   */
  protected PlainFractionMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
