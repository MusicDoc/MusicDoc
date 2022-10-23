package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ChordSymbolMapper} for {@link SongFormatAbc}.
 */
public class ChordSymbolMapperAbc extends ChordSymbolMapper {

  /** The singleton instance. */
  public static final ChordSymbolMapperAbc INSTANCE = new ChordSymbolMapperAbc();

  /**
   * The constructor.
   */
  protected ChordSymbolMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
