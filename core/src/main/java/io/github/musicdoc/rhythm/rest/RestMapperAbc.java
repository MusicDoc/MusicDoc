package io.github.musicdoc.rhythm.rest;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link RestMapper} for {@link SongFormatAbc}.
 */
public class RestMapperAbc extends RestMapper {

  /** The singleton instance. */
  public static final RestMapperAbc INSTANCE = new RestMapperAbc();

  /**
   * The constructor.
   */
  protected RestMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
