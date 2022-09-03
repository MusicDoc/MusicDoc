package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link PlainFractionMapper} for {@link SongFormatAbc}.
 */
public class PlainFractionMapperAbc extends PlainFractionMapper {

  /** The singleton instance. */
  public static final PlainFractionMapperAbc INSTANCE = new PlainFractionMapperAbc();

  /**
   * The constructor.
   */
  protected PlainFractionMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
