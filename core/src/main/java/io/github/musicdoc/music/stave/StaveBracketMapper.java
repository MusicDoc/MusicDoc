package io.github.musicdoc.music.stave;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

public class StaveBracketMapper extends AbstractMapper<StaveBracket> {

    public static final StaveBracketMapper INSTANCE = new StaveBracketMapper();

    @Override
    public StaveBracket parse(CharStream chars) {
        String syntax = chars.peek(2);
        StaveBracket bracket = StaveBracket.ofSyntax(syntax);
        if (bracket != null) {
            chars.skip(2);
        }
        return bracket;
    }

    @Override
    public void format(StaveBracket bracket, Appendable buffer, SongFormatOptions options) throws IOException {
        if (bracket == null) {
            return;
        }
        buffer.append(bracket.getSyntax());
    }
}
