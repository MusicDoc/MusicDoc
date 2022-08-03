package io.github.musicdoc.score.section;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ScoreSectionNameMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreSectionNameMapperOpenSong extends ScoreSectionNameMapperBase {

  /** The singleton instance. */
  public static final ScoreSectionNameMapperOpenSong INSTANCE = new ScoreSectionNameMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreSectionNameMapperOpenSong() {

    super("" + SECTION_START, SECTION_END);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
