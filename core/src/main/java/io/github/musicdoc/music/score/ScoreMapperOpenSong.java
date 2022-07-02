package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapperOpenSong;

/**
 * {@link ScoreMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc ChordPro format}.
 */
public class ScoreMapperOpenSong extends ScoreMapper {

  /** The singleton instance. */
  public static final ScoreMapperOpenSong INSTANCE = new ScoreMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  protected ScoreLineMapper getLineMapper() {

    return ScoreLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return ScoreSectionNameMapperOpenSong.INSTANCE;
  }
}
