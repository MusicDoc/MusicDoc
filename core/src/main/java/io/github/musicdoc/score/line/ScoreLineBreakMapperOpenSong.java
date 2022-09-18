package io.github.musicdoc.score.line;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link ScoreLineBreakMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreLineBreakMapperOpenSong extends ScoreLineBreakMapperBase {

  /** The singleton instance. */
  public static final ScoreLineBreakMapperOpenSong INSTANCE = new ScoreLineBreakMapperOpenSong();

  /**
   * The constructor.
   */
  protected ScoreLineBreakMapperOpenSong() {

    super();
  }

  @Override
  protected String getSyntax(ScoreLineBreak lineBreak, SongFormatContext context) {

    return null;
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
