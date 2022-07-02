package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapperMusicDoc;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapperMusicDoc;

/**
 * {@link ScoreLineMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc MusicDoc format}.
 */
public class ScoreLineMapperMusicDoc extends ScoreLineMapper {

  /** The singleton instance. */
  public static final ScoreLineMapperMusicDoc INSTANCE = new ScoreLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreLineMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreVoiceLineMapper getVoiceLineMapper() {

    return ScoreVoiceLineMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreCommentLineMapper getCommentLineMapper() {

    return ScoreCommentLineMapperMusicDoc.INSTANCE;
  }
}
