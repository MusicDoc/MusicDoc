package io.github.musicdoc.music.instrument;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link InstrumentMapper} for {@link SongFormatAbc}.
 */
public class InstrumentMapperAbc extends InstrumentMapper {

  /** The singleton instnace. */
  public static final InstrumentMapperAbc INSTANCE = new InstrumentMapperAbc();

  /**
   * The constructor.
   */
  protected InstrumentMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
