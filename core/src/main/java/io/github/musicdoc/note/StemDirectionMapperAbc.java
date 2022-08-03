package io.github.musicdoc.note;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link StemDirectionMapper} for {@link SongFormatAbc}.
 */
public class StemDirectionMapperAbc extends StemDirectionMapper {

  /** The singleton instance. */
  public static final StemDirectionMapperAbc INSTANCE = new StemDirectionMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
