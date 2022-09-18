package io.github.musicdoc.score.line;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;

/**
 * {@link ScoreLineBreakMapper} for {@link SongFormatAbc}.
 */
public class ScoreLineBreakMapperAbc extends ScoreLineBreakMapperBase {

  /** The singleton instance. */
  public static final ScoreLineBreakMapperAbc INSTANCE = new ScoreLineBreakMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreLineBreakMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected String getSyntax(ScoreLineBreak lineBreak, SongFormatContext context) {

    String syntax = null;
    if (lineBreak == ScoreLineBreak.BREAK) {
      // syntax = context.getProperty();
    }
    if (syntax == null) {
      syntax = super.getSyntax(lineBreak, context);
    }
    return syntax;
  }

}
