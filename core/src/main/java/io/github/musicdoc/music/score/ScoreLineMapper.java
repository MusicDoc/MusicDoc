package io.github.musicdoc.music.score;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.score.comment.ScoreCommentLine;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;

/**
 * {@link AbstractMapper Mapper} for {@link ScoreLine}.
 */
public abstract class ScoreLineMapper extends AbstractMapper<ScoreLine<?, ?>> {

  @Override
  public ScoreLine<?, ?> parse(MusicInputStream chars, SongFormatOptions options) {

    ScoreCommentLine comment = getCommentLineMapper().parse(chars, options);
    if (comment != null) {
      return comment;
    }
    if (chars.skipNewline()) {
      return ScoreEmptyLine.INSTANCE;
    }
    return getVoiceLineMapper().parse(chars, options);
  }

  /**
   * @return the {@link ScoreCommentLineMapper}.
   */
  protected abstract ScoreCommentLineMapper getCommentLineMapper();

  /**
   * @return the {@link ScoreVoiceLineMapper}.
   */
  protected abstract ScoreVoiceLineMapper getVoiceLineMapper();

  @Override
  public void format(ScoreLine<?, ?> line, MusicOutputStream out, SongFormatOptions options) {

    if (line instanceof ScoreVoiceLine) {
      getVoiceLineMapper().format((ScoreVoiceLine) line, out, options);
    } else if (line instanceof ScoreCommentLine) {
      getCommentLineMapper().format((ScoreCommentLine) line, out, options);
    } else if (line instanceof ScoreEmptyLine) {
      // nothing to do
      out.append(NEWLINE);
    } else {
      throw new IllegalStateException(line.getClass().getName());
    }
  }

}
