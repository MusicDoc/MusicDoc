package io.github.musicdoc.music.tone;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link Tone}.
 */
public abstract class ToneMapper extends AbstractMapper<Tone> {

  @Override
  public Tone parse(MusicInputStream chars, SongFormatOptions options) {

    TonePitch pitch = getTonePitchMapper().parse(chars, options);
    // TODO support for absolute tones specifying octave...
    if (pitch == null) {
      return null;
    }
    int octave = 4;
    Clef clef = options.getClef();
    if (clef != null) {
      Tone lowTone = clef.getLowTone();
      octave = lowTone.getOctave();
    }
    if (pitch.isLowercase()) {
      octave++;
    }
    while (true) {
      char c = chars.peek();
      if (c == Tone.OCTAVE_UP) {
        octave++;
      } else if (c == Tone.OCTAVE_DOWN) {
        octave--;
      } else {
        break;
      }
      chars.next();
    }
    return Tone.of(pitch, octave);
  }

  /**
   * @return the {@link TonePitchMapper}.
   */
  protected abstract TonePitchMapper getTonePitchMapper();

  @Override
  public void format(Tone tone, MusicOutputStream out, SongFormatOptions options) {

    out.append(tone.getName());
  }
}
