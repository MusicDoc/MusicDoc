package io.github.musicdoc.music.harmony;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.tone.TonePitchMapper;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalKey}.
 */
public abstract class MusicalKeyMapper extends AbstractMapper<MusicalKey> {

  @Override
  public MusicalKey parse(MusicInputStream chars, SongFormatOptions options) {

    TonePitch tonika = getTonePitchMapper().parse(chars, options);
    if (tonika == null) {
      return null;
    }
    chars.skipWhile(' '); // for ABC compatibility
    // detect tonal system (maj/min)...
    TonalSystem tonalSystem = getTonalSystemMapper().parse(chars, options);
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
  public void format(MusicalKey key, MusicOutputStream out, SongFormatOptions options) {

    if (key == null) {
      return;
    }
    TonePitch tonika = key.getTonika();
    if (options.isNormalizeMusicalKeys()) {
      tonika = tonika.with(options.getToneNameStyle());
    }
    getTonePitchMapper().format(tonika, out, options);
    getTonalSystemMapper().format(key.getSystem(), out, options);
  }

  /**
   * @return the {@link TonePitchMapper}.
   */
  protected abstract TonePitchMapper getTonePitchMapper();

  /**
   * @return the {@link TonalSystemMapper}.
   */
  protected abstract TonalSystemMapper getTonalSystemMapper();
}
