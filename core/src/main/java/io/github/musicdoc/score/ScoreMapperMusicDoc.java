package io.github.musicdoc.score;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ScoreMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreMapperMusicDoc extends ScoreMapperBase {

  /** The singleton instance. */
  public static final ScoreMapperMusicDoc INSTANCE = new ScoreMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
