package io.github.musicdoc.tone;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperMusicDoc extends ToneMapperBase {

  /** The singleton instance. */
  public static final ToneMapperMusicDoc INSTANCE = new ToneMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ToneMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
