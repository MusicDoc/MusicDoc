package io.github.musicdoc.harmony;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link TonalSystemMapper} for {@link SongFormatMusicDoc}.
 */
public class TonalSystemMapperMusicDoc extends TonalSystemMapper {

  /** The singleton instance. */
  public static final TonalSystemMapperMusicDoc INSTANCE = new TonalSystemMapperMusicDoc();

  /**
   * The constructor.
   */
  protected TonalSystemMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
