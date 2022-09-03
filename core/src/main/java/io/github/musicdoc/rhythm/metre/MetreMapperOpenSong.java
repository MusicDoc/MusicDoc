package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link MetreMapper} for {@link SongFormatMusicDoc}.
 */
public class MetreMapperOpenSong extends MetreMapper {

  /** The singleton instance. */
  public static final MetreMapperOpenSong INSTANCE = new MetreMapperOpenSong();

  /**
   * The constructor.
   */
  protected MetreMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
