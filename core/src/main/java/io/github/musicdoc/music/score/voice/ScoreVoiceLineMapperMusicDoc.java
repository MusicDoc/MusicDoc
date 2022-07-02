package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;

/**
 * {@link ScoreVoiceLineMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc MusicDoc format}.
 */
public class ScoreVoiceLineMapperMusicDoc extends ScoreVoiceLineMapper {

  /** The singleton instance. */
  public static final ScoreVoiceLineMapperMusicDoc INSTANCE = new ScoreVoiceLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreVoiceLineMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public ScoreVoiceLine parse(MusicInputStream chars, SongFormatOptions options) {

    char c = chars.peek();
    if ((c == ScoreCommentLineMapper.BEGIN_COMMENT) || ListCharFilter.NEWLINE.accept(c)) {
      return null;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    ScoreVoiceLineContinuation continuation = ScoreVoiceLineContinuation.of(c);
    if (continuation != null) {
      chars.next();
      line.setContinuation(continuation);
    }
    while (chars.hasNext() && !chars.skipNewline()) {
      long index = chars.getIndex();
      ScoreVoiceCell cell = getVoiceCellMapper().parse(chars, options);
      if (chars.getIndex() > index) {
        line.addCell(cell);
      } else {
        // ups, parser error - prevent infinity loop
        // todo: log error
        chars.next();
      }
    }
    return line;
  }

  @Override
  public void format(ScoreVoiceLine line, MusicOutputStream out, SongFormatOptions options) {

    if (line == null) {
      return;
    }
    ScoreVoiceLineContinuation continuation = line.getContinuation();
    if (continuation != null) {
      out.append(continuation.getSymbol());
    }
    ScoreVoiceCellMapper voiceCellMapper = getVoiceCellMapper();
    for (ScoreVoiceCell cell : line.getCells()) {
      voiceCellMapper.format(cell, out, options);
    }
    out.append(NEWLINE);
  }

  /**
   * @return the {@link ScoreVoiceCellMapper}
   */
  protected ScoreVoiceCellMapper getVoiceCellMapper() {

    return ScoreVoiceCellMapperMusicDoc.INSTANCE;
  }
}
