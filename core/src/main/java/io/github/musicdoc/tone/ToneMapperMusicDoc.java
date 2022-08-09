package io.github.musicdoc.tone;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperMusicDoc extends ToneMapperBase {

  private static final char START_OCTAVE = ':';

  private static final char END_OCTAVE = ':';

  /** The singleton instance. */
  public static final ToneMapperMusicDoc INSTANCE = new ToneMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ToneMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected Tone readOctave(MusicInputStream in, SongFormatContext context, TonePitch pitch) {

    if (in.expect(START_OCTAVE)) {
      Integer octaveInteger = in.readInteger(2, true);
      if (octaveInteger == null) {
        in.addError("Missing octave");
      }
      in.expect(END_OCTAVE, true);
      int octave = 4; // fallback
      if (octaveInteger != null) {
        octave = octaveInteger.intValue();
      }
      return Tone.of(pitch, octave, true);
    }
    return super.readOctave(in, context, pitch);
  }

  @Override
  public void write(Tone tone, MusicOutputStream out, SongFormatContext context) {

    if ((tone != null) && tone.isAbsolute()) {
      getTonePitchMapper().write(tone.getPitch());
      out.write(START_OCTAVE);
      out.write(Integer.toString(tone.getOctave()));
      out.write(END_OCTAVE);
    }
    super.write(tone, out, context);
  }

}
