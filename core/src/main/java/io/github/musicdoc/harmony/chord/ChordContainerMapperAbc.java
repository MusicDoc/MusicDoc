package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ChordContainerMapper} for {@link SongFormatAbc}.
 */
public class ChordContainerMapperAbc extends ChordContainerMapperBase {

  /** The singleton instance. */
  public static final ChordContainerMapperAbc INSTANCE = new ChordContainerMapperAbc();

  /**
   * The constructor.
   */
  public ChordContainerMapperAbc() {

    super('"', '"');
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
