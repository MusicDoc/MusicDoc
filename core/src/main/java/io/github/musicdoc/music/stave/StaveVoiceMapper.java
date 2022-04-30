package io.github.musicdoc.music.stave;

import java.io.IOException;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.music.instrument.Instrument;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link StaveVoice}.
 */
public class StaveVoiceMapper extends AbstractMapper<StaveVoice> {

    private static final CharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(PROPERTIES_KEY_VALUE_SEPARATOR, PROPERTIES_SEPARATOR, VOICE_SEPARATOR, STAVE_END);

    public static final StaveVoiceMapper INSTANCE = new StaveVoiceMapper();

    @Override
    public StaveVoice parse(CharStream chars) {
        String name = chars.readUntil(STOP_FILTER, true);
        String shortcut = null;
        Instrument instrument = null;
        if (chars.expect(VOICE_SEPARATOR, false)) {
            shortcut = chars.readUntil(STOP_FILTER, true);
            if (chars.expect(VOICE_SEPARATOR, false)) {
                instrument = InstrumentMapper.INSTANCE.parse(chars);
            }
        }
        return new StaveVoice(name, shortcut, instrument);
    }

    @Override
    public void format(StaveVoice voice, Appendable buffer, SongFormatOptions options) throws IOException {
        if (voice == null) {
            return;
        }
        buffer.append(voice.getName());
        String abbreviation = voice.getAbbreviation();
        if (abbreviation != null) {
            buffer.append(VOICE_SEPARATOR);
            buffer.append(abbreviation);
        }
        Instrument instrument = voice.getInstrument();
        if (instrument != null) {
            if (abbreviation == null) {
                buffer.append(VOICE_SEPARATOR);
            }
            buffer.append(VOICE_SEPARATOR);
            InstrumentMapper.INSTANCE.format(instrument, buffer, options);
        }
    }
}
