package io.github.musicdoc.music.rythm.rest;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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
