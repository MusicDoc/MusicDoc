package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ChordMapper} for {@link SongFormatOpenSong}.
 */
public class ChordMapperOpenSong extends ChordMapper {

  /** The singleton instance. */
  public static final ChordMapperOpenSong INSTANCE = new ChordMapperOpenSong();

  /**
   * The constructor.
   */
  protected ChordMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
