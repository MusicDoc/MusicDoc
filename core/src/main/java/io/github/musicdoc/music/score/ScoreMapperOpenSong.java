package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ScoreMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreMapperOpenSong extends ScoreMapper {

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
