package io.github.musicdoc.score;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link ScoreMapper} for {@link SongFormatAbc}.
 */
public class ScoreMapperAbc extends ScoreMapperBase {

  /** The singleton instance. */
  public static final ScoreMapperAbc INSTANCE = new ScoreMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
