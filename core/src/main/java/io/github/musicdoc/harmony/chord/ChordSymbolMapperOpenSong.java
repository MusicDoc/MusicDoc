package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ChordSymbolMapper} for {@link SongFormatOpenSong}.
 */
public class ChordSymbolMapperOpenSong extends ChordSymbolMapper {

  /** The singleton instance. */
  public static final ChordSymbolMapperOpenSong INSTANCE = new ChordSymbolMapperOpenSong();

  /**
   * The constructor.
   */
  protected ChordSymbolMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
