package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link BarLineTypeMapper} for {@link SongFormatMusicDoc}.
 */
public class BarLineTypeMapperMusicDoc extends BarLineTypeMapperBase {

  /** The singleton instance. */
  public static final BarLineTypeMapperMusicDoc INSTANCE = new BarLineTypeMapperMusicDoc();

  /**
   * The constructor.
   */
  protected BarLineTypeMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
