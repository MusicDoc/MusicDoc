package io.github.musicdoc.score.line;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.cell.ScoreCellMapper;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;

/**
 * {@link ScoreLineMapper} for {@link SongFormatAbc}.
 */
public abstract class ScoreLineMapperBase extends ScoreLineMapper {

  /**
   * {@link ListCharFilter} {@link ListCharFilter#accept(char) accepting} the end of a
   * {@link io.github.musicdoc.score.cell.ScoreCell#getLyric() lyric segment}.
   */
  protected static final ListCharFilter LYRIC_END_FILTER = ListCharFilter.allOf(' ', '-', '_');

  private final String voiceStart;

  private final String voiceEnd;

  private final String voiceEndFormat;

  private final String commentPrefix;

  /**
   * The constructor.
   *
   * @param voiceStart the start sequence of the {@link StaveVoice#getId() voice ID}.
   * @param voiceEnd the end sequence of the {@link StaveVoice#getId() voice ID}.
   * @param commentPrefix the prefix for {@link ScoreLine#getComment() comments}.
   */
  protected ScoreLineMapperBase(String voiceStart, String voiceEnd, String commentPrefix) {

    super();
    this.voiceStart = voiceStart;
    this.voiceEnd = voiceEnd;
    if (voiceEnd.endsWith(" ")) {
      this.voiceEndFormat = voiceEnd;
    } else {
      this.voiceEndFormat = voiceEnd + " ";
    }
    this.commentPrefix = commentPrefix;
  }

  @Override
  public ScoreLine read(MusicInputStream in, SongFormatContext context) {

    if (in.expect(this.commentPrefix, false)) {
      String comment = in.readLine();
      return new ScoreCommentLine(comment);
    } else if (in.skipNewline()) {
      return ScoreEmptyLine.INSTANCE;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    String voiceId = null;
    if ((this.voiceStart != null) && in.expect(this.voiceStart, false)) {
      in.skipWhile(' '); // be tolerant
      voiceId = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
      in.skipWhile(' '); // be tolerant
      if (!in.expect(this.voiceEnd, false)) {
        in.addError("Missing voice end marker (" + this.voiceEnd + ").");
      }
    }
    StaveVoiceContainer voiceContainer = context.getStaveVoiceContainer();
    if (voiceContainer != null) {
      StaveVoice voice = voiceContainer.getVoice(voiceId);
      if (voice != null) {
        line.setVoice(voice);
        context.setStave(voice.getStave());
      }
    }
    while (in.hasNext() && !in.skipNewline()) {
      ScoreCell cell = getScoreCellMapper().read(in, context);
      if (cell == null) {
        in.addError("Missing voice cell - ignoring character to prevent infinity loop.");
        in.next();
      } else {
        line.add(cell);
      }
    }
    return line;
  }

  @Override
  public void write(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    if (line == null) {
      return;
    }
    String comment = line.getComment();
    if (comment == null) {
      StaveVoice voice = line.getVoice();
      if ((voice != null) && (this.voiceStart != null)) {
        String id = voice.getId();
        if ((id != null) && !id.isEmpty()) {
          out.write(this.voiceStart);
          out.write(id);
          out.write(this.voiceEndFormat);
        }
        context.setStave(voice.getStave());
      }
      ScoreCellMapper voiceCellMapper = getScoreCellMapper();
      for (ScoreCell cell : line.getCells()) {
        voiceCellMapper.write(cell, out, context);
      }
    } else if (line != ScoreEmptyLine.INSTANCE) {
      out.write(this.commentPrefix);
      out.write(comment);
    }
    out.write(NEWLINE_CHAR);
  }

}
