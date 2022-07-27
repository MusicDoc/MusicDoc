package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.stave.voice.StaveVoice;

/**
 * {@link ScoreVoiceLineMapper} for {@link SongFormatAbc}.
 */
public abstract class ScoreVoiceLineMapperBase extends ScoreVoiceLineMapper {

  private final String voiceStart;

  private final String voiceEnd;

  private final String voiceEndFormat;

  /**
   * The constructor.
   *
   * @param voiceStart the start sequence of the {@link StaveVoice#getId() voice ID}.
   * @param voiceEnd the end sequence of the {@link StaveVoice#getId() voice ID}.
   */
  protected ScoreVoiceLineMapperBase(String voiceStart, String voiceEnd) {

    super();
    this.voiceStart = voiceStart;
    this.voiceEnd = voiceEnd;
    if (voiceEnd.endsWith(" ")) {
      this.voiceEndFormat = voiceEnd;
    } else {
      this.voiceEndFormat = voiceEnd + " ";
    }
  }

  @Override
  public ScoreVoiceLine read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    if ((c == ScoreCommentLineMapper.BEGIN_COMMENT) || ListCharFilter.NEWLINE.accept(c)) {
      return null;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    if (in.expect(this.voiceStart, false)) {
      in.skipWhile(' '); // be tolerant
      String voiceId = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
      in.skipWhile(' '); // be tolerant
      if (!in.expect(this.voiceEnd, false)) {
        in.addError("Missing voice end marker (" + this.voiceEnd + ").");
      }
      line.setVoice(new StaveVoice(voiceId)); // just a marker that will be resolved and replaced by higher level mapper

      // TODO can not set Stave in context as only resolved after this parsing but StaveChange requires Stave to be
      // present for inheritance - avoid inheritance in properties and resolve at runtime in getters?
    }
    while (in.hasNext() && !in.skipNewline()) {
      ScoreVoiceCell cell = getVoiceCellMapper().read(in, context);
      if (cell == null) {
        in.addError("Missing voice cell - ignoring character to prevent infinity loop.");
        in.next();
      } else {
        line.addCell(cell);
      }
    }
    return line;
  }

  @Override
  public void write(ScoreVoiceLine line, MusicOutputStream out, SongFormatContext context) {

    if (line == null) {
      return;
    }
    StaveVoice voice = line.getVoice();
    if (voice != null) {
      String id = voice.getId();
      if ((id != null) && !id.isEmpty()) {
        out.write(this.voiceStart);
        out.write(id);
        out.write(this.voiceEndFormat);
      }
      context.setStave(voice.getStave());
    }
    ScoreVoiceCellMapper voiceCellMapper = getVoiceCellMapper();
    for (ScoreVoiceCell cell : line.getCells()) {
      voiceCellMapper.write(cell, out, context);
    }
    out.write(NEWLINE);
  }

}
