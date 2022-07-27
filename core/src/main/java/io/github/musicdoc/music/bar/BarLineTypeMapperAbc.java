package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link BarLineTypeMapper} for {@link SongFormatAbc}.
 */
public class BarLineTypeMapperAbc extends BarLineTypeMapper {

  /** The singleton instance. */
  public static final BarLineTypeMapperAbc INSTANCE = new BarLineTypeMapperAbc();

  /**
   * The constructor.
   */
  protected BarLineTypeMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
