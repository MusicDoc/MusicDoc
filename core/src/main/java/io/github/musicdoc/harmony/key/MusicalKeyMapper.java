package io.github.musicdoc.harmony.key;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalKey}.
 */
public abstract class MusicalKeyMapper extends AbstractMapper<MusicalKey> {

  @Override
  public MusicalKey read(MusicInputStream in, SongFormatContext context) {

    TonePitch tonika = getTonePitchMapper().read(in, context);
    if (tonika == null) {
      return null;
    }
    CharStreamScanner scanner = in.getScanner();
    scanner.skipWhile(' '); // for ABC compatibility
    // detect tonal system (maj/min)...
    TonalSystem tonalSystem = getTonalSystemMapper().read(in, context);
    if (tonalSystem == null) {
      if (tonika.isLowercase()) {
        tonalSystem = TonalSystem.MINOR_M;
      } else {
        tonalSystem = TonalSystem.MAJOR_EMPTY;
      }
    }
    return MusicalKey.of(tonika, tonalSystem);
  }

  @Override
  public void write(MusicalKey key, MusicOutputStream out, SongFormatContext context) {

    if (key == null) {
      return;
    }
    TonePitch tonika = key.getTonika();
    if (context.isNormalizeMusicalKeys()) {
      tonika = tonika.with(context.getToneNameStyle());
    }
    getTonePitchMapper().write(tonika, out, context);
    TonalSystem system = key.getSystem();
    getTonalSystemMapper().write(system, out, context);
  }

}
