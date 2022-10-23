package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ChordSymbolMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordSymbolMapperMusicDoc extends ChordSymbolMapper {

  /** The singleton instance. */
  public static final ChordSymbolMapperMusicDoc INSTANCE = new ChordSymbolMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ChordSymbolMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
