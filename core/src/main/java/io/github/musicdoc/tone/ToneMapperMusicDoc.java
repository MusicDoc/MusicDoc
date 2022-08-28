package io.github.musicdoc.tone;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperMusicDoc extends ToneMapperBase {

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

    String lookahead = in.peek(3);
    int length = lookahead.length();
    if (length > 0) {
      char c0 = lookahead.charAt(0);
      if (ListCharFilter.DIGITS.accept(c0) && (length >= 2)) {
        char c1 = lookahead.charAt(1);
        if ((c1 == END_OCTAVE)
            || (ListCharFilter.DIGITS.accept(c1) && (length == 3) && (lookahead.charAt(2) == END_OCTAVE))) {
          Integer octaveInteger = in.readInteger(2, true);
          assert (octaveInteger != null);
          in.expect(END_OCTAVE, true);
          return Tone.of(pitch, octaveInteger.intValue(), true);
        }
      }
    }
    return super.readOctave(in, context, pitch);
  }

}
