package io.github.musicdoc.music.score.comment;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreCommentLine}.
 */
public abstract class ScoreCommentLineMapper extends AbstractMapper<ScoreCommentLine> {

  public static final char BEGIN_COMMENT = ';';

  @Override
  public ScoreCommentLine parse(MusicInputStream chars, SongFormatOptions options) {

    char c = chars.peek();
    if (c == BEGIN_COMMENT) {
      chars.next();
      String comment = chars.readUntil(ListCharFilter.NEWLINE, true);
      chars.skipNewline();
      return new ScoreCommentLine(comment);
    }
    return null;
  }

  @Override
  public void format(ScoreCommentLine line, MusicOutputStream out, SongFormatOptions options) {

    out.append(BEGIN_COMMENT);
    out.append(line.getComment());
    out.append(NEWLINE);
  }
}
