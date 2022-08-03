package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
