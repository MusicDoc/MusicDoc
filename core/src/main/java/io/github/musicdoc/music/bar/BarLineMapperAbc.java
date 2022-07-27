package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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
