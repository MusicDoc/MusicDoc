package io.github.musicdoc.music.harmony;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalKey}.
 */
public class MusicalKeyMapper extends AbstractMapper<MusicalKey> {

  /** The singleton instance. */
  public static final MusicalKeyMapper INSTANCE = new MusicalKeyMapper();

  @Override
  public MusicalKey parse(CharStream chars) {

    TonePitch tonika = TonePitchMapper.INSTANCE.parse(chars);
    if (tonika == null) {
      return null;
    }
    chars.skipWhile(' '); // for ABC compatibility
    // detect tonal system (maj/min)...
    TonalSystem tonalSystem = TonalSystemMapper.INSTANCE.parse(chars);
    if (tonalSystem == null) {
      if (tonika.isLowercase()) {
        tonalSystem = TonalSystem.MINOR;
      } else {
        tonalSystem = TonalSystem.MAJOR;
      }
    }
    return MusicalKey.of(tonika, tonalSystem);
  }

  @Override
  public void format(MusicalKey key, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (key == null) {
      return;
    }
    TonePitch tonika = key.getTonika();
    if (options.isNormalizeMusicalKeys()) {
      tonika = tonika.with(options.getToneNameStyle());
    }
    TonePitchMapper.INSTANCE.format(tonika, buffer, options);
    TonalSystemMapper.INSTANCE.format(key.getSystem(), buffer, options);
  }
}
