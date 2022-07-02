package io.github.musicdoc.music.harmony.chord;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.tone.ToneNameCase;
import io.github.musicdoc.music.tone.ToneNameStyle;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.tone.TonePitchMapper;

/**
 * {@link AbstractMapper Mapper} for {@link Chord}.
 */
public abstract class ChordMapper extends AbstractMapper<Chord> {

  /** Separator character for base tone. */
  public static final char BASE_TONE_SEPARATOR = '/';

  @Override
  public Chord parse(MusicInputStream chars, SongFormatOptions options) {

    chars.skipWhile(' ');
    // detect fundamental tone
    TonePitch fundamentalTone = getTonePitchMapper().parse(chars, options);
    if (fundamentalTone == null) {
      return null;
    }
    // Amaj may conflict with Amaj7
    List<ChordExtension> extensions = new ArrayList<>();
    ChordExtension extension = getChordExtensionMapper().parse(chars, options);
    // detect tonal tonalSystem (maj/min)...
    TonalSystem tonalSystem = null;
    if (extension == null) {
      tonalSystem = getTonalSystemMapper().parse(chars, options);
    }
    if (tonalSystem == null) {
      if (fundamentalTone.isLowercase()) {
        tonalSystem = TonalSystem.MINOR_EMPTY;
      } else {
        tonalSystem = TonalSystem.MAJOR_EMPTY;
      }
    }
    // detect (further) chord extensions
    boolean startExt = (extension != null);
    do {
      if (!startExt) {
        extension = getChordExtensionMapper().parse(chars, options);
      }
      startExt = false;
      if (extension != null) {
        if (extension.isRemoveThird()) {
          tonalSystem = null;
        }
        extensions.add(extension);
      }
    } while (extension != null);
    // detect base tone
    TonePitch baseTone = fundamentalTone;
    if (chars.expect(BASE_TONE_SEPARATOR)) {
      baseTone = getTonePitchMapper().parse(chars, options);
      if (baseTone == null) {
        // actually a parse error...
        baseTone = fundamentalTone;
      } else if (baseTone.getNameStyle() != fundamentalTone.getNameStyle()) {
        if (fundamentalTone.getName().length() == 1) {
          fundamentalTone = fundamentalTone.with(baseTone.getNameStyle());
        } else if (baseTone.getName().length() == 1) {
          baseTone = baseTone.with(fundamentalTone.getNameStyle());
        }
      }
    }
    return new Chord(fundamentalTone, tonalSystem, baseTone, extensions);
  }

  /**
   * @return the {@link TonalSystemMapper}.
   */
  protected abstract TonalSystemMapper getTonalSystemMapper();

  /**
   * @return the {@link ChordExtensionMapper}.
   */
  protected abstract ChordExtensionMapper getChordExtensionMapper();

  /**
   * @return the {@link TonePitchMapper}.
   */
  protected abstract TonePitchMapper getTonePitchMapper();

  @Override
  public void format(Chord chord, MusicOutputStream out, SongFormatOptions options) {

    if (chord == null) {
      return;
    }
    if (options.isNormalizeChords()) {
      TonePitch fundamental = chord.getFundamental().getNormalForm();
      TonalSystem tonalSystem = chord.getTonalSystem();
      ToneNameStyle<?> toneNameStyle = options.getToneNameStyle();
      if (tonalSystem.isMinor()) {
        fundamental = fundamental.with(toneNameStyle, ToneNameCase.LOWER_CASE);
      } else if (tonalSystem.isMajor()) {
        fundamental = fundamental.with(toneNameStyle, ToneNameCase.CAPITAL_CASE);
      } else {
        fundamental = fundamental.with(toneNameStyle);
      }
      getTonePitchMapper().format(fundamental, out, options);
      if (tonalSystem.isMinor()) {
        out.append('m');
      }
      for (ChordExtension ext : chord.getExtensions()) {
        getChordExtensionMapper().format(ext, out, options);
      }
      TonePitch base = chord.getBase();
      if (base.getStep() != fundamental.getStep()) {
        out.append(BASE_TONE_SEPARATOR);
        base = base.with(toneNameStyle);
        getTonePitchMapper().format(base, out, options);
      }
    } else {
      out.append(chord.toString());
    }
  }
}
