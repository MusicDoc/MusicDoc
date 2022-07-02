package io.github.musicdoc.music.score.section;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link ScoreSectionNameMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreSectionNameMapperOpenSong extends ScoreSectionNameMapper {

  /** The singleton instance. */
  public static final ScoreSectionNameMapperOpenSong INSTANCE = new ScoreSectionNameMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreSectionNameMapperOpenSong() {

    super("" + SECTION_START);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
