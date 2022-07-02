package io.github.musicdoc.music.stave;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.instrument.Instrument;
import io.github.musicdoc.music.instrument.InstrumentMapper;

/**
 * {@link AbstractMapper Mapper} for {@link StaveVoice}.
 */
public abstract class StaveVoiceMapper extends AbstractMapper<StaveVoice> {

  private static final CharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(PROPERTIES_KEY_VALUE_SEPARATOR_CHAR,
      PROPERTIES_SEPARATOR_CHAR, VOICE_SEPARATOR, STAVE_END);

  @Override
  public StaveVoice parse(MusicInputStream chars, SongFormatOptions options) {

    String name = chars.readUntil(STOP_FILTER, true);
    String shortcut = null;
    Instrument instrument = null;
    if (chars.expect(VOICE_SEPARATOR, false)) {
      shortcut = chars.readUntil(STOP_FILTER, true);
      if (chars.expect(VOICE_SEPARATOR, false)) {
        instrument = getInstrumentMapper().parse(chars, options);
      }
    }
    return new StaveVoice(name, shortcut, instrument);
  }

  /**
   * @return the {@link InstrumentMapper}.
   */
  protected abstract InstrumentMapper getInstrumentMapper();

  @Override
  public void format(StaveVoice voice, MusicOutputStream out, SongFormatOptions options) {

    if (voice == null) {
      return;
    }
    out.append(voice.getName());
    String abbreviation = voice.getAbbreviation();
    if (abbreviation != null) {
      out.append(VOICE_SEPARATOR);
      out.append(abbreviation);
    }
    Instrument instrument = voice.getInstrument();
    if (instrument != null) {
      if (abbreviation == null) {
        out.append(VOICE_SEPARATOR);
      }
      out.append(VOICE_SEPARATOR);
      getInstrumentMapper().format(instrument, out, options);
    }
  }
}
