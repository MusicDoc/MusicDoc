package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ChordMapper} for {@link SongFormatAbc}.
 */
public class ChordMapperAbc extends ChordMapper {

  /** The singleton instance. */
  public static final ChordMapperAbc INSTANCE = new ChordMapperAbc();

  /**
   * The constructor.
   */
  protected ChordMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
