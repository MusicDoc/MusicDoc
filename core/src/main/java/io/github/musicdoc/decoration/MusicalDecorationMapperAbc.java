package io.github.musicdoc.decoration;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link MusicalDecorationMapper} for {@link SongFormatAbc}.
 */
public class MusicalDecorationMapperAbc extends MusicalDecorationMapper {

  /** The singleton instance. */
  public static final MusicalDecorationMapperAbc INSTANCE = new MusicalDecorationMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalDecorationMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
