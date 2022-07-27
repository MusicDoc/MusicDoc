package io.github.musicdoc.music.score.comment;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ScoreCommentLineMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreCommentLineMapperOpenSong extends ScoreCommentLineMapperAbc {

  /** The singleton instance. */
  public static final ScoreCommentLineMapperOpenSong INSTANCE = new ScoreCommentLineMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreCommentLineMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
