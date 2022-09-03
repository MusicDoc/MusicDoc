package io.github.musicdoc.tone;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.tone.pitch.TonePitch;
import io.github.musicdoc.tone.pitch.TonePitchChange;
import io.github.musicdoc.tone.pitch.TonePitchMapperBase;
import io.github.musicdoc.tone.pitch.TonePitches;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperAbc extends ToneMapperBase {

  /** The singleton instance. */
  public static final ToneMapperAbc INSTANCE = new ToneMapperAbc();

  private static final char NEUTRALIZE = '=';

  private static final char FLATTEN = '_';

  private static final char SHARPEN = '^';

  /**
   * The constructor.
   */
  protected ToneMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected TonePitch readPitch(MusicInputStream in, SongFormatContext context) {

    // In ABC format, pitches are written relative to current key and previous accidental signs
    // the parsing logic is intentionally kept here instead of TonePitchMapperAbc because that is also used to
    // parse pitches for chords or keys where these changes shall not apply.
    EnharmonicType type = null;
    if (in.expect(SHARPEN)) {
      type = EnharmonicType.SINGLE_SHARP;
    } else if (in.expect(FLATTEN)) {
      type = EnharmonicType.SINGLE_FLAT;
    } else if (in.expect(NEUTRALIZE)) {
      type = EnharmonicType.NORMAL;
    }
    char c = in.peek();
    if (!TonePitchMapperBase.FILTER_TONE_START.accept(c)) {
      if (type != null) {
        in.addError("Missing pitch after accidental sign.");
      }
      return null;
    }
    in.next();
    TonePitch pitch = TonePitches.of(Character.toString(c));
    TonePitchChange tonePitchChange = context.getTonePitchChange();
    pitch = tonePitchChange.resolve(pitch, type);
    return pitch;
  }

  @Override
  protected boolean isSupportAbsoluteOctave() {

    return false;
  }

  @Override
  protected void writePitch(TonePitch pitch, MusicOutputStream out, SongFormatContext context) {

    TonePitchChange tonePitchChange = context.getTonePitchChange();
    EnharmonicType type = tonePitchChange.unresolve(pitch);
    if (type == EnharmonicType.NORMAL) {
      out.write(NEUTRALIZE);
    } else if (type == EnharmonicType.SINGLE_SHARP) {
      out.write(SHARPEN);
      // pitch = pitch.flatten();
    } else if (type == EnharmonicType.SINGLE_FLAT) {
      out.write(FLATTEN);
      // pitch = pitch.sharpen();
    }
    super.writePitch(pitch, out, context);
  }

}
