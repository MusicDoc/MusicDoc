package io.github.musicdoc.score.line;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ScoreLineBreakMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreLineBreakMapperMusicDoc extends ScoreLineBreakMapperBase {

  /** The singleton instance. */
  public static final ScoreLineBreakMapperMusicDoc INSTANCE = new ScoreLineBreakMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreLineBreakMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
