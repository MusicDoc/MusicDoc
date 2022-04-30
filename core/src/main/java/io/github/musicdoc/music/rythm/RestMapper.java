package io.github.musicdoc.music.rythm;

import java.io.IOException;
import java.util.List;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link io.github.musicdoc.format.AbstractMapper Mapper} for {@link Rest}.
 */
public class RestMapper extends AbstractValuedItemMapper<Rest> {

    public static final RestMapper INSTANCE = new RestMapper();

    private static final ListCharFilter FILTER_REST_START = new ListCharFilter(new char[] {REST_VISIBLE, REST_INVISIBLE}, true);

    @Override
    protected Rest parseContent(CharStream chars, List<ValuedItemDecoration> decorations) {
        char c = chars.peek();
        boolean invisible;
        if (c == REST_VISIBLE) {
            invisible = false;
        } else if (c == REST_INVISIBLE) {
            invisible = true;
        } else {
            return null;
        }
        chars.next();
        MusicalValue value = MusicalValueMapper.INSTANCE.parse(chars);
        if (value == null) {
            value = MusicalValue._1_1;
        }
        return new Rest(value, invisible, decorations);
    }

    @Override
    protected void formatContent(Rest item, Appendable buffer, SongFormatOptions options) throws IOException {
        if (item == null) {
            return;
        }
        buffer.append(item.getSymbol());
    }
}
