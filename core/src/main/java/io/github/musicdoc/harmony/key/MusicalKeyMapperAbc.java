package io.github.musicdoc.harmony.key;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link MusicalKeyMapper} for {@link SongFormatAbc}.
 */
public class MusicalKeyMapperAbc extends MusicalKeyMapper {

  /** The singleton instance. */
  public static final MusicalKeyMapperAbc INSTANCE = new MusicalKeyMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalKeyMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
