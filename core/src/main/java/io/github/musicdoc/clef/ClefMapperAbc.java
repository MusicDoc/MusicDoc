package io.github.musicdoc.clef;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ClefMapper} for {@link SongFormatAbc}.
 */
public class ClefMapperAbc extends ClefMapperBase {

  /** The singleton instance. */
  public static final ClefMapperAbc INSTANCE = new ClefMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
