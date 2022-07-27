package io.github.musicdoc.music.score.comment;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ScoreCommentLineMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreCommentLineMapperMusicDoc extends ScoreCommentLineMapper {

  public static final char BEGIN_COMMENT = ';';

  /** The singleton instance. */
  public static final ScoreCommentLineMapperMusicDoc INSTANCE = new ScoreCommentLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreCommentLineMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public ScoreCommentLine read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    if (c == BEGIN_COMMENT) {
      in.next();
      String comment = in.readLine();
      return new ScoreCommentLine(comment);
    }
    return null;
  }

  @Override
  public void write(ScoreCommentLine line, MusicOutputStream out, SongFormatContext context) {

    out.write(BEGIN_COMMENT);
    out.write(line.getComment());
    out.write(NEWLINE);
  }
}
