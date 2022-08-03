package io.github.musicdoc.harmony;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link TonalSystemMapper} for {@link SongFormatAbc}.
 */
public class TonalSystemMapperAbc extends TonalSystemMapper {

  /** The singleton instance. */
  public static final TonalSystemMapperAbc INSTANCE = new TonalSystemMapperAbc();

  /**
   * The constructor.
   */
  protected TonalSystemMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
