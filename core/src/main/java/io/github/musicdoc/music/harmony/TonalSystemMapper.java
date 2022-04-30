package io.github.musicdoc.music.harmony;

import java.io.IOException;
import java.util.Locale;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

public class TonalSystemMapper extends AbstractMapper<TonalSystem> {

    // needs to be changed when early tonal systems as hypomixolydian are added
    private static final ListCharFilter SYSTEM_FILTER = ListCharFilter.allOfAnyCase("MAJORIN");

    public static final TonalSystemMapper INSTANCE = new TonalSystemMapper();

    @Override
    public TonalSystem parse(CharStream chars) {
        int maxLen = 5; // needs to be increased when early tonal systems as hypomixolydian are added
        String lookahead = chars.peekWhile(SYSTEM_FILTER, maxLen).toLowerCase(Locale.US);
        for (int i = lookahead.length(); i > 0; i--) {
            String name = lookahead.substring(0, i);
            TonalSystem tonalSystem = TonalSystem.of(name);
            if (tonalSystem != null) {
                chars.skip(i);
                return tonalSystem;
            }
        }
        return null;
    }

    @Override
    public void format(TonalSystem tonalSystem, Appendable buffer, SongFormatOptions options) throws IOException {
        if (tonalSystem == null) {
            return;
        }
        buffer.append(tonalSystem.getName());
    }
}
