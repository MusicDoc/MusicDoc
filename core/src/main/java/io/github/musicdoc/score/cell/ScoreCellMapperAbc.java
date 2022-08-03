package io.github.musicdoc.score.cell;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ScoreCellMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreCellMapperAbc extends ScoreCellMapperBase {

  /** The singleton instance. */
  public static final ScoreCellMapperAbc INSTANCE = new ScoreCellMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreCellMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
