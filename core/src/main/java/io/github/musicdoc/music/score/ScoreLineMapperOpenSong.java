package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ScoreLineMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreLineMapperOpenSong extends ScoreLineMapper {

  /** The singleton instance. */
  public static final ScoreLineMapperOpenSong INSTANCE = new ScoreLineMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreLineMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
