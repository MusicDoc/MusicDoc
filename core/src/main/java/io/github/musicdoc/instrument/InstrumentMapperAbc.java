package io.github.musicdoc.instrument;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

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
