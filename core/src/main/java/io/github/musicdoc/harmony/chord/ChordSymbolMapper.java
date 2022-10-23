package io.github.musicdoc.harmony.chord;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.tone.ToneNameCase;
import io.github.musicdoc.tone.ToneNameStyle;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * {@link AbstractMapper Mapper} for {@link ChordSymbol}.
 */
public abstract class ChordSymbolMapper extends AbstractMapper<ChordSymbol> {

  /** Separator character for base tone. */
  public static final char BASE_TONE_SEPARATOR = '/';

  @Override
  public ChordSymbol read(MusicInputStream in, SongFormatContext context) {

    in.skipWhile(' ');
    // detect fundamental tone
    TonePitch fundamentalTone = getTonePitchMapper().read(in, context);
    if (fundamentalTone == null) {
      return null;
    }
    // Amaj may conflict with Amaj7
    List<ChordExtension> extensions = new ArrayList<>();
    ChordExtension extension = getChordExtensionMapper().read(in, context);
    // detect tonal tonalSystem (maj/min)...
    TonalSystem tonalSystem = null;
    if (extension == null) {
      tonalSystem = getTonalSystemMapper().read(in, context);
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
        extension = getChordExtensionMapper().read(in, context);
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
    if (in.expect(BASE_TONE_SEPARATOR)) {
      baseTone = getTonePitchMapper().read(in, context);
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
    return new ChordSymbol(fundamentalTone, tonalSystem, baseTone, extensions);
  }

  @Override
  public void write(ChordSymbol chord, MusicOutputStream out, SongFormatContext context) {

    if (chord == null) {
      return;
    }
    if (context.isNormalizeChords()) {
      TonePitch fundamental = chord.getFundamental().getNormalForm();
      TonalSystem tonalSystem = chord.getTonalSystem();
      ToneNameStyle<?> toneNameStyle = context.getToneNameStyle();
      if (tonalSystem.isMinor()) {
        fundamental = fundamental.with(toneNameStyle, ToneNameCase.LOWER_CASE);
      } else if (tonalSystem.isMajor()) {
        fundamental = fundamental.with(toneNameStyle, ToneNameCase.CAPITAL_CASE);
      } else {
        fundamental = fundamental.with(toneNameStyle);
      }
      getTonePitchMapper().write(fundamental, out, context);
      if (tonalSystem.isMinor()) {
        out.write('m');
      }
      for (ChordExtension ext : chord.getExtensions()) {
        getChordExtensionMapper().write(ext, out, context);
      }
      TonePitch base = chord.getBase();
      if (base.getStep() != fundamental.getStep()) {
        out.write(BASE_TONE_SEPARATOR);
        base = base.with(toneNameStyle);
        getTonePitchMapper().write(base, out, context);
      }
    } else {
      out.write(chord.toString());
    }
  }
}
