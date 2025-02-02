package io.github.musicdoc.stave.system;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.stave.StaveBracket;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * {@link StaveSystemMapper} for {@link SongFormatAbc}.
 */
public class StaveSystemMapperAbc extends StaveSystemMapper {

  private static final String PREFIX_SCORE = "%%score ";

  /** The singleton instance. */
  public static final StaveSystemMapperAbc INSTANCE = new StaveSystemMapperAbc();

  @Override
  public StaveSystem read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    if (!scanner.expect(PREFIX_SCORE, false)) {
      return null;
    }
    StaveSystemState state = new StaveSystemState(true);
    char c;
    do {
      c = scanner.next();
      if ((c == '(') || (c == '{') || (c == '[')) {
        state.start(StaveBracket.ofStart(c), in);
      } else if ((c == ')') || (c == ']') || (c == '}')) {
        state.end(StaveBracket.ofEnd(c), in);
      } else if (c == '|') {
        state.setDisconnected(false);
      } else if (c == '*') {
        state.setFloating(true);
        readVoiceId(in, state);
      } else if (c == ' ') {
        state.handleVoice();
      } else if (CharFilter.LATIN_LETTER_OR_DIGIT.accept(c)) {
        state.appendVoiceId(c);
        readVoiceId(in, state);
      } else {
        scanner.addError("Unexpected character '" + c + "'");
      }
    } while ((c != NEWLINE_CHAR) && (c != 0));
    StaveSystem group = state.getTopSystem(in);
    assert (group != null);
    context.setStaveSystem(group);
    while (scanner.peekString(2).equals("V:")) {
      String property = in.readPropertyStart();
      assert (property.equals("V"));
      StaveVoice voice = getStaveVoiceMapper().read(in, context);
      assert (group.getVoice(voice.getId()) == voice);
    }
    group.makeImmutable();
    return group;
  }

  private void readVoiceId(MusicInputStream in, StaveSystemState state) {

    String id = in.getScanner().readWhile(CharFilter.LATIN_LETTER_OR_DIGIT);
    state.appendVoiceId(id);
    state.handleVoice();
  }

  @Override
  public void write(StaveSystem system, MusicOutputStream out, SongFormatContext context) {

    out.write(PREFIX_SCORE);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
