package io.github.musicdoc.score.line;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * Abstract base implementation of {@link ScoreLineBreakMapper}.
 */
public abstract class ScoreLineBreakMapperBase extends ScoreLineBreakMapper {

  /**
   * @param lineBreak the {@link ScoreLineBreak} to read or write.
   * @param context the {@link SongFormatContext}.
   * @return the syntax to read or write the given {@link ScoreLineBreak} or {@code null} if unsupported by this format.
   */
  protected String getSyntax(ScoreLineBreak lineBreak, SongFormatContext context) {

    if (lineBreak == ScoreLineBreak.BREAK) {
      return "$";
    }
    return null;
  }

  @Override
  public ScoreLineBreak read(MusicInputStream in, SongFormatContext context) {

    for (ScoreLineBreak lineBreak : ScoreLineBreak.values()) {
      String syntax = getSyntax(lineBreak, context);
      if (syntax != null) {
        if (in.expect(syntax, false)) {
          return lineBreak;
        }
      }
    }
    return null;
  }

  @Override
  public void write(ScoreLineBreak lineBreak, MusicOutputStream out, SongFormatContext context) {

    if (lineBreak == null) {
      return;
    }
    String syntax = getSyntax(lineBreak, context);
    if (syntax != null) {
      out.write(syntax);
    }
  }

}
