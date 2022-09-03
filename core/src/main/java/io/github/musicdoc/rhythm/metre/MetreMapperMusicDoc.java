package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link MetreMapper} for {@link SongFormatOpenSong}.
 */
public class MetreMapperMusicDoc extends MetreMapper {

  /** The singleton instance. */
  public static final MetreMapperMusicDoc INSTANCE = new MetreMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MetreMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
