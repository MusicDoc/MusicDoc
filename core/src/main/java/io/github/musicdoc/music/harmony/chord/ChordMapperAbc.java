package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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
