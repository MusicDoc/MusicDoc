package io.github.musicdoc.music.instrument;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

public class InstrumentMapper extends AbstractMapper<Instrument> {

    public static final InstrumentMapper INSTANCE = new InstrumentMapper();

    @Override
    public Instrument parse(CharStream chars) {
        // TODO
        return null;
    }

    @Override
    public void format(Instrument instrument, Appendable buffer, SongFormatOptions options) throws IOException {
        if (instrument == null) {
            return;
        }
        // TODO
    }
}
