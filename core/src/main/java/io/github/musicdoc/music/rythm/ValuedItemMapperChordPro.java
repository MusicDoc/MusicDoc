package io.github.musicdoc.music.rythm;

import java.io.IOException;

import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

public class ValuedItemMapperChordPro extends ValuedItemMapper {

    public static final ValuedItemMapperChordPro INSTANCE = new ValuedItemMapperChordPro();

    @Override
    public ValuedItem<?> parse(CharStream chars) {
        ValuedItem<?> item = null;
        if (chars.expect(ITEM_START)) {
            item = super.parse(chars);
            chars.expect(ITEM_END, true);
        }
        return item;
    }

    @Override
    public void format(ValuedItem<?> item, Appendable buffer, SongFormatOptions options) throws IOException {
        if (item == null) {
            return;
        }
        buffer.append(ITEM_START);
        super.format(item, buffer, options);
        buffer.append(ITEM_END);
    }
}