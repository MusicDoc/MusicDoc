package io.github.musicdoc.rythm.rest;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link RestMapper} for {@link SongFormatMusicDoc}.
 */
public class RestMapperMusicDoc extends RestMapper {

  /** The singleton instance. */
  public static final RestMapperMusicDoc INSTANCE = new RestMapperMusicDoc();

  /**
   * The constructor.
   */
  protected RestMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
