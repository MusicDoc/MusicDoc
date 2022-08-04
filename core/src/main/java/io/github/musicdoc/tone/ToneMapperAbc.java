package io.github.musicdoc.tone;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.tone.pitch.TonePitch;
import io.github.musicdoc.tone.pitch.TonePitchChange;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperAbc extends ToneMapper {

  /** The singleton instance. */
  public static final ToneMapperAbc INSTANCE = new ToneMapperAbc();

  /**
   * The constructor.
   */
  protected ToneMapperAbc() {

    super();
  }

  @Override
  protected TonePitch readPitch(MusicInputStream in, SongFormatContext context) {

    // In ABC format, pitches are written relative to current key and previous accidental signs
    // the parsing logic is intentionally kept here instead of TonePitchMapperAbc because that may also be used to
    // parse pitches for chords or keys where these changes shall not apply.
    EnharmonicType type = null;
    if (in.expect('^')) {
      type = EnharmonicType.SINGLE_SHARP;
    } else if (in.expect('_')) {
      type = EnharmonicType.SINGLE_FLAT;
    } else if (in.expect('=')) {
      type = EnharmonicType.NORMAL;
    }
    TonePitch pitch = super.readPitch(in, context);
    if (pitch == null) {
      if (type != null) {
        in.addError("Missing pitch after accidental sign.");
      }
      return null;
    }
    TonePitchChange tonePitchChange = context.getTonePitchChange();
    pitch = tonePitchChange.resolve(pitch, type);
    return pitch;
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
