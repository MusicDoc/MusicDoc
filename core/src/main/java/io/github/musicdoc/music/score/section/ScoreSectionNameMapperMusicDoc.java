package io.github.musicdoc.music.score.section;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ScoreSectionNameMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreSectionNameMapperMusicDoc extends ScoreSectionNameMapperBase {

  /** The singleton instance. */
  public static final ScoreSectionNameMapperMusicDoc INSTANCE = new ScoreSectionNameMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreSectionNameMapperMusicDoc() {

    super("#" + SECTION_START, SECTION_END);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
