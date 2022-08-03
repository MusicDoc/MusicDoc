package io.github.musicdoc.score.section;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ScoreSectionNameMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreSectionNameMapperAbc extends ScoreSectionNameMapperBase {

  /** The singleton instance. */
  public static final ScoreSectionNameMapperAbc INSTANCE = new ScoreSectionNameMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreSectionNameMapperAbc() {

    super("%%text ", NEWLINE_CHAR);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
