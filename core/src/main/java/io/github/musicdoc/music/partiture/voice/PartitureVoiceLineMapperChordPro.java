package io.github.musicdoc.music.partiture.voice;

import java.io.IOException;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.music.partiture.comment.PartitureCommentLineMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link PartitureVoiceLineMapper} for {@link io.github.musicdoc.format.SongFormatChordPro ChordPro format}.
 */
public class PartitureVoiceLineMapperChordPro extends PartitureVoiceLineMapper {

    public static final PartitureVoiceLineMapperChordPro INSTANCE = new PartitureVoiceLineMapperChordPro();

    @Override
    public PartitureVoiceLine parse(CharStream chars) {
        char c = chars.peek();
        if ((c == PartitureCommentLineMapper.BEGIN_COMMENT) || ListCharFilter.NEWLINE.accept(c)) {
            return null;
        }
        PartitureVoiceLine line = new PartitureVoiceLine();
        PartitureVoiceLineContinuation continuation = PartitureVoiceLineContinuation.of(c);
        if (continuation != null) {
            chars.next();
            line.setContinuation(continuation);
        }
        while (chars.hasNext() && !chars.skipNewline()) {
            long index = chars.getIndex();
            PartitureVoiceCell cell = PartitureVoiceCellMapperChordPro.INSTANCE.parse(chars);
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
    public void format(PartitureVoiceLine line, Appendable buffer, SongFormatOptions options) throws IOException {
        if (line == null) {
            return;
        }
        PartitureVoiceLineContinuation continuation = line.getContinuation();
        if (continuation != null) {
            buffer.append(continuation.getSymbol());
        }
        for (PartitureVoiceCell cell : line.getCells()) {
            PartitureVoiceCellMapperChordPro.INSTANCE.format(cell, buffer, options);
        }
        buffer.append(NEWLINE);
    }
}
