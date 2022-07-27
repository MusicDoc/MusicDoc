package io.github.musicdoc.music.volta;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link VoltaMapper} for {@link SongFormatAbc}.
 */
public class VoltaMapperAbc extends VoltaMapper {

  /** The singleton instance. */
  public static final VoltaMapperAbc INSTANCE = new VoltaMapperAbc();

  /**
   * The constructor.
   */
  protected VoltaMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
