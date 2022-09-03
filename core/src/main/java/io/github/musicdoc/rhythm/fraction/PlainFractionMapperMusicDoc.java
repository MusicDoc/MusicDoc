package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link PlainFractionMapper} for {@link SongFormatMusicDoc}.
 */
public class PlainFractionMapperMusicDoc extends PlainFractionMapper {

  /** The singleton instance. */
  public static final PlainFractionMapperMusicDoc INSTANCE = new PlainFractionMapperMusicDoc();

  /**
   * The constructor.
   */
  protected PlainFractionMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
