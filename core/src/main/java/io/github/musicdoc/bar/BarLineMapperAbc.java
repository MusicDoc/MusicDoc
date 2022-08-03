package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link BarLineMapper} for {@link SongFormatAbc}.
 */
public class BarLineMapperAbc extends BarLineMapper {

  /** The singleton instance. */
  public static final BarLineMapperAbc INSTANCE = new BarLineMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
