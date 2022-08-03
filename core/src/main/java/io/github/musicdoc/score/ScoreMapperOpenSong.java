package io.github.musicdoc.score;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ScoreMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreMapperOpenSong extends ScoreMapperBase {

  /** The singleton instance. */
  public static final ScoreMapperOpenSong INSTANCE = new ScoreMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
