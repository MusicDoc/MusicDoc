package io.github.musicdoc.music.stave;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Bar}.
 */
public class BarMapper extends AbstractMapper<Bar> {

    public static final BarMapper INSTANCE = new BarMapper();

    @Override
    public Bar parse(CharStream chars) {
        BarType type = BarTypeMapper.INSTANCE.parse(chars);
        if (type == null) {
            return null;
        }
        // actually ABC notation also allows appending "[1" or even " [1"
        // not yet supported here, and also kind of colliding with chord pro
        int ending = 0;
        Integer endingValue = chars.readInteger(2, false);
        if (endingValue != null) {
            ending = endingValue.intValue();
        }
        if (ending > 1) {
            assert (!type.isRepeat());
        }
        return new Bar(type, ending);
    }

    @Override
    public void format(Bar bar, Appendable buffer, SongFormatOptions options) throws IOException {
        if (bar == null) {
            return;
        }
        BarTypeMapper.INSTANCE.format(bar.getType(), buffer, options);
        int ending = bar.getEnding();
        if (ending > 0) {
            buffer.append(Integer.toString(ending));
        }
    }
}
