package io.github.musicdoc.tone;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * Base implementation of {@link ToneMapper}.
 */
public abstract class ToneMapperBase extends ToneMapper {

  @Override
  public Tone read(MusicInputStream in, SongFormatContext context) {

    TonePitch pitch = readPitch(in, context);
    if (pitch == null) {
      return null;
    }
    return readOctave(in, context, pitch);
  }

  /**
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @return the parsed {@link TonePitch} or {@code null} if none found.
   */
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
    boolean absolute = tone.isAbsolute();
    int octave = tone.getOctave();
    if (!absolute) {
      int baseOctave = getOctave(context, pitch);
      octave = octave - baseOctave;
    }
    writePitch(pitch, octave, absolute, out, context);
  }

  /**
   * @param pitch the {@link TonePitch} to write.
   * @param octave the absolute or relative {@link Tone#getOctave() tone's octave}.
   * @param absolute a flag indicating if the given {@code octave} is {@link Tone#isAbsolute() absolute} or relative to
   *        the base-octave of the current stave and clef.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writePitch(TonePitch pitch, int octave, boolean absolute, MusicOutputStream out,
      SongFormatContext context) {

    if (absolute) {
      pitch = pitch.with(ToneNameCase.CAPITAL_CASE);
      writePitch(pitch, out, context);
      out.write(Integer.toString(octave));
      out.write(':');
    } else {
      if (octave > 0) {
        pitch = pitch.with(ToneNameCase.LOWER_CASE);
        writePitch(pitch, out, context);
        while (octave > 1) {
          octave--;
          out.write(Tone.OCTAVE_UP);
        }
      } else {
        pitch = pitch.with(ToneNameCase.CAPITAL_CASE);
        writePitch(pitch, out, context);
        while (octave < 0) {
          octave++;
          out.write(Tone.OCTAVE_DOWN);
        }
      }

    }
  }

  /**
   * @param pitch the {@link TonePitch} to write.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writePitch(TonePitch pitch, MusicOutputStream out, SongFormatContext context) {

    getTonePitchMapper().write(pitch, out, context);
  }

}