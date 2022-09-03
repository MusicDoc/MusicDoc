package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link MetreMapper} for {@link SongFormatAbc}.
 */
public class MetreMapperAbc extends MetreMapper {

  /** The singleton instance. */
  public static final MetreMapperAbc INSTANCE = new MetreMapperAbc();

  /**
   * The constructor.
   */
  protected MetreMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
