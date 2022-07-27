package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link ValuedItemMapper} for {@link SongFormatAbc}.
 */
public class ValuedItemMapperAbc extends ValuedItemMapper {

  /** The singleton instance. */
  public static final ValuedItemMapperAbc INSTANCE = new ValuedItemMapperAbc();

  /**
   * The constructor.
   */
  protected ValuedItemMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
