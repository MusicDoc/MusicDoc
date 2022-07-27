package io.github.musicdoc.music.score;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.score.comment.ScoreCommentLine;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreLine}.
 */
public abstract class ScoreLineMapper extends AbstractMapper<ScoreLine<?, ?>> {

  @Override
  public ScoreLine<?, ?> read(MusicInputStream in, SongFormatContext context) {

    ScoreCommentLine comment = getCommentLineMapper().read(in, context);
    if (comment != null) {
      return comment;
    }
    if (in.skipNewline()) {
      return ScoreEmptyLine.INSTANCE;
    }
    return getVoiceLineMapper().read(in, context);
  }

  @Override
  public void write(ScoreLine<?, ?> line, MusicOutputStream out, SongFormatContext context) {

    if (line instanceof ScoreVoiceLine) {
      getVoiceLineMapper().write((ScoreVoiceLine) line, out, context);
    } else if (line instanceof ScoreCommentLine) {
      getCommentLineMapper().write((ScoreCommentLine) line, out, context);
    } else if (line instanceof ScoreEmptyLine) {
      // nothing to do
      out.write(NEWLINE);
    } else {
      throw new IllegalStateException(line.getClass().getName());
    }
  }

}
