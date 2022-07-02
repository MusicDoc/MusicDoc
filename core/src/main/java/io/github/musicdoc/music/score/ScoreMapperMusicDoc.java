package io.github.musicdoc.music.score;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapperMusicDoc;

/**
 * {@link ScoreMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc MusicDoc format}.
 */
public class ScoreMapperMusicDoc extends ScoreMapper {

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

  @Override
  protected ScoreLineMapper getLineMapper() {

    return ScoreLineMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return ScoreSectionNameMapperMusicDoc.INSTANCE;
  }
}
