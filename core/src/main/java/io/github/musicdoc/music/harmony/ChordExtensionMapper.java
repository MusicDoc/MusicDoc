package io.github.musicdoc.music.harmony;

import java.io.IOException;
import java.util.Locale;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link ChordExtension}.
 */
public class ChordExtensionMapper extends AbstractMapper<ChordExtension> {

    private static final ListCharFilter EXT_FILTER = ListCharFilter.allOfAnyCase("MAJORINDSU0123456789+Δ°");

    public static final ChordExtensionMapper INSTANCE = new ChordExtensionMapper();

    @Override
    public ChordExtension parse(CharStream chars) {
        String lookahead = chars.peekWhile(EXT_FILTER, 6);
        lookahead = lookahead.toLowerCase(Locale.US);
        for (int i = lookahead.length(); i > 0; i--) {
            String name = lookahead.substring(0, i);
            ChordExtension ext = ChordExtension.of(name);
            if (ext != null) {
                chars.skip(i);
                return ext;
            }
        }
        return null;
    }

    @Override
    public void format(ChordExtension extension, Appendable buffer, SongFormatOptions options) throws IOException {
        if (extension == null) {
            return;
        }
        if (options.isNormalizeChordExtensions()) {
            extension = extension.getReference();
        }
        buffer.append(extension.getName());
    }
}
