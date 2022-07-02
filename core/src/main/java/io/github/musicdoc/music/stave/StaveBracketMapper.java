package io.github.musicdoc.music.stave;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link StaveBracket}.
 */
public abstract class StaveBracketMapper extends AbstractMapper<StaveBracket> {

  @Override
  public StaveBracket parse(MusicInputStream chars, SongFormatOptions options) {

    String syntax = chars.peek(2);
    StaveBracket bracket = StaveBracket.ofSyntax(syntax);
    if (bracket != null) {
      chars.skip(2);
    }
    return bracket;
  }

  @Override
  public void format(StaveBracket bracket, MusicOutputStream out, SongFormatOptions options) {

    if (bracket == null) {
      return;
    }
    out.append(bracket.getSyntax());
  }
}
