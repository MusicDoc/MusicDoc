package io.github.musicdoc.score.line;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ScoreLineMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreLineMapperMusicDoc extends ScoreLineMapperBase {

  /** The singleton instance. */
  public static final ScoreLineMapperMusicDoc INSTANCE = new ScoreLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreLineMapperMusicDoc() {

    super("$", " ", "%");
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
