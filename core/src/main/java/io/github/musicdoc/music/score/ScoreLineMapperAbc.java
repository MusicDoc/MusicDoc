package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link ScoreLineMapper} for {@link SongFormatAbc}.
 */
public class ScoreLineMapperAbc extends ScoreLineMapper {

  /** The singleton instance. */
  public static final ScoreLineMapperAbc INSTANCE = new ScoreLineMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreLineMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
