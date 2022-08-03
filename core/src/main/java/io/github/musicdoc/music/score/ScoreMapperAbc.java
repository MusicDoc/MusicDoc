package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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
