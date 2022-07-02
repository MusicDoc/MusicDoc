package io.github.musicdoc.music.instrument;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
