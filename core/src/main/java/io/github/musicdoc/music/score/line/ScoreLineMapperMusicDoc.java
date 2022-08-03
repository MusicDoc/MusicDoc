package io.github.musicdoc.music.score.line;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
