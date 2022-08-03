package io.github.musicdoc.instrument;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link InstrumentMapper} for {@link SongFormatMusicDoc}.
 */
public class InstrumentMapperMusicDoc extends InstrumentMapper {

  /** The singleton instnace. */
  public static final InstrumentMapperMusicDoc INSTANCE = new InstrumentMapperMusicDoc();

  /**
   * The constructor.
   */
  protected InstrumentMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
