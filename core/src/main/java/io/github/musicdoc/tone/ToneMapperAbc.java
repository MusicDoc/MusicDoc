package io.github.musicdoc.tone;

import io.github.mmm.scanner.CharStreamScanner;
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

    CharStreamScanner scanner = in.getScanner();
    // In ABC format, pitches are written relative to current key and previous accidental signs
    // the parsing logic is intentionally kept here instead of TonePitchMapperAbc because that is also used to
    // parse pitches for chords or keys where these changes shall not apply.
    EnharmonicType type = null;
    if (scanner.expectOne(SHARPEN)) {
      type = EnharmonicType.SINGLE_SHARP;
    } else if (scanner.expectOne(FLATTEN)) {
      type = EnharmonicType.SINGLE_FLAT;
    } else if (scanner.expectOne(NEUTRALIZE)) {
      type = EnharmonicType.NORMAL;
    }
    char c = scanner.peek();
    if (!TonePitchMapperBase.FILTER_TONE_START.accept(c)) {
      if (type != null) {
        scanner.addError("Missing pitch after accidental sign.");
      }
      return null;
    }
    scanner.next();
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
