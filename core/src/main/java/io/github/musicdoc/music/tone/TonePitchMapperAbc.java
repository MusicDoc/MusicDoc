package io.github.musicdoc.music.tone;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link TonePitchMapper} for {@link SongFormatAbc}.
 */
public class TonePitchMapperAbc extends TonePitchMapper {

  /** The singleton instance. */
  public static final TonePitchMapperAbc INSTANCE = new TonePitchMapperAbc();

  /**
   * The constructor.
   */
  protected TonePitchMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public TonePitch read(MusicInputStream in, SongFormatContext context) {

    EnharmonicType type = null;
    if (in.expect('^')) {
      type = EnharmonicType.SINGLE_SHARP;
    } else if (in.expect('_')) {
      type = EnharmonicType.SINGLE_FLAT;
    } else if (in.expect('=')) {
      type = EnharmonicType.NORMAL;
    }
    TonePitch pitch = super.read(in, context);
    if (type == EnharmonicType.SINGLE_SHARP) {
      pitch = pitch.sharpen();
    } else if (type == EnharmonicType.SINGLE_FLAT) {
      pitch = pitch.flatten();
    }
    return pitch;
  }

}
