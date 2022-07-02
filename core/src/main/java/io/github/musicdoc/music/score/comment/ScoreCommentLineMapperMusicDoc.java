package io.github.musicdoc.music.score.comment;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ScoreCommentLineMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreCommentLineMapperMusicDoc extends ScoreCommentLineMapper {

  /** The singleton instance. */
  public static final ScoreCommentLineMapperMusicDoc INSTANCE = new ScoreCommentLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreCommentLineMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
