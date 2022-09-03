package io.github.musicdoc.rhythm.item;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ValuedItemMapper} for {@link SongFormatAbc}.
 */
public class ValuedItemMapperAbc extends ValuedItemMapperBase {

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
