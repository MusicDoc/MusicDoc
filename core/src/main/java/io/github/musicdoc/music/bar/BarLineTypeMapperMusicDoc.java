package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
