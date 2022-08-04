package io.github.musicdoc.tone;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * {@link AbstractMapper Mapper} for {@link Tone}.
 */
public abstract class ToneMapper extends AbstractMapper<Tone> {

  @Override
  public Tone read(MusicInputStream in, SongFormatContext context) {

    TonePitch pitch = readPitch(in, context);
    if (pitch == null) {
      return null;
    }
    return readOctave(in, context, pitch);
  }

  protected TonePitch readPitch(MusicInputStream in, SongFormatContext context) {

    return getTonePitchMapper().read(in, context);
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @param pitch the already parsed pitch.
   * @return the {@link Tone}.
   */
  protected Tone readOctave(MusicInputStream in, SongFormatContext context, TonePitch pitch) {

    int octave = getOctave(context, pitch);
    while (true) {
      char c = in.peek();
      if (c == Tone.OCTAVE_UP) {
        octave++;
      } else if (c == Tone.OCTAVE_DOWN) {
        octave--;
      } else {
        break;
      }
      in.next();
    }
    return Tone.of(pitch, octave);
  }

  private int getOctave(SongFormatContext context, TonePitch pitch) {

    int octave = 4;
    StaveVoice voice = context.getStaveVoice();
    if (voice == null) {
      Clef clef = context.getClef();
      if (clef == null) {
        clef = context.getClef();
      }
    } else {
      Stave stave = voice.getStave();
      if (stave != null) {
        Clef clef = stave.getClef();
        if (clef != null) {
          octave = clef.getMiddleTone().getOctave();
        }
      }
      octave += voice.getOctaveShift();
    }
    if (pitch.isLowercase()) {
      octave++;
    }
    return octave;
  }

  @Override
  public void write(Tone tone, MusicOutputStream out, SongFormatContext context) {

    if (tone == null) {
      return;
    }
    TonePitch pitch = tone.getPitch();
    int baseOctave = getOctave(context, pitch);
    int octave = tone.getOctave();
    int delta = octave - baseOctave;
    if (delta > 0) {
      pitch = pitch.with(ToneNameCase.LOWER_CASE);
      getTonePitchMapper().write(pitch);
      while (delta > 1) {
        delta--;
        out.write(Tone.OCTAVE_UP);
      }
    } else {
      pitch = pitch.with(ToneNameCase.CAPITAL_CASE);
      getTonePitchMapper().write(pitch);
      while (delta < 0) {
        delta++;
        out.write(Tone.OCTAVE_DOWN);
      }
    }
  }

}
