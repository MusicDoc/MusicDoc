package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapperOpenSong;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapperOpenSong;

/**
 * {@link ScoreLineMapper} for {@link io.github.musicdoc.music.format.SongFormatOpenSong OpenSong format}.
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

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreVoiceLineMapper getVoiceLineMapper() {

    return ScoreVoiceLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreCommentLineMapper getCommentLineMapper() {

    return ScoreCommentLineMapperOpenSong.INSTANCE;
  }
}
