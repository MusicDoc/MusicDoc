package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ChordExtensionMapper} for {@link SongFormatAbc}.
 */
public class ChordExtensionMapperAbc extends ChordExtensionMapper {

  /** The singleton instance. */
  public static final ChordExtensionMapperAbc INSTANCE = new ChordExtensionMapperAbc();

  /**
   * The constructor.
   */
  protected ChordExtensionMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
