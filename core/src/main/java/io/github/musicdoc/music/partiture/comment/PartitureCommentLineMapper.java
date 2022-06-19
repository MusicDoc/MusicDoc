package io.github.musicdoc.music.partiture.comment;

import java.io.IOException;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link PartitureCommentLine}.
 */
public class PartitureCommentLineMapper extends AbstractMapper<PartitureCommentLine> {

    public static final char BEGIN_COMMENT = ';';

    public static final PartitureCommentLineMapper INSTANCE = new PartitureCommentLineMapper();

    @Override
    public PartitureCommentLine parse(CharStream chars) {
        char c = chars.peek();
        if (c == BEGIN_COMMENT) {
            chars.next();
            String comment = chars.readUntil(ListCharFilter.NEWLINE, true);
            chars.skipNewline();
            return new PartitureCommentLine(comment);
        }
        return null;
    }

    @Override
    public void format(PartitureCommentLine line, Appendable buffer, MusicFormatOptions options) throws IOException {
        buffer.append(BEGIN_COMMENT);
        buffer.append(line.getComment());
        buffer.append(NEWLINE);
    }
}
