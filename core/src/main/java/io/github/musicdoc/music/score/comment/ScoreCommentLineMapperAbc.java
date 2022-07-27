package io.github.musicdoc.music.score.comment;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link ScoreCommentLineMapper} for {@link SongFormatAbc}.
 */
public class ScoreCommentLineMapperAbc extends ScoreCommentLineMapper {

  /** The singleton instance. */
  public static final ScoreCommentLineMapperAbc INSTANCE = new ScoreCommentLineMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreCommentLineMapperAbc() {

    super();
  }

  @Override
  public ScoreCommentLine read(MusicInputStream in, SongFormatContext context) {

    String lookahead = in.peek(2);
    if ("%%".equals(lookahead)) {
      return null;
    } else if (lookahead.startsWith("%")) {
      in.next();
      String comment = in.readLine();
      return new ScoreCommentLine(comment);
    }
    return null;
  }

  @Override
  public void write(ScoreCommentLine line, MusicOutputStream out, SongFormatContext context) {

    out.write("%");
    out.write(line.getComment());
    out.write(NEWLINE);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
