package io.github.musicdoc.tone;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsAccidentals;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link TonePitch}.
 */
public abstract class TonePitchMapper extends AbstractMapper<TonePitch> {

  /** {@link CharFilter} that {@link CharFilter#accept(char) accepts} start characters of a {@link TonePitch}. */
  public static final ListCharFilter FILTER_TONE_START = ListCharFilter.allOfAnyCase("ABCDEFGH");

  /** {@link CharFilter} that {@link CharFilter#accept(char) accepts} start characters of a {@link TonePitch}. */
  public static final ListCharFilter FILTER_TONE = FILTER_TONE_START.join('i', 'I', 's', 'S', '#', 'b',
      UnicodeGlyphsAccidentals.NEUTRAL_CHAR, UnicodeGlyphsAccidentals.FLAT_1_CHAR,
      UnicodeGlyphsAccidentals.SHARP_1_CHAR, UnicodeGlyphsAccidentals.SIGN_2_CHAR1,
      UnicodeGlyphsAccidentals.FLAT_2_CHAR2, UnicodeGlyphsAccidentals.SHARP_2_CHAR2);

  @Override
  public TonePitch read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    if (!FILTER_TONE_START.accept(c)) {
      return null;
    }
    String lookahead = in.peekWhile(FILTER_TONE, 5); // maximum length of TonePitch.name is 5 (e.g. "Cisis")
    int length = lookahead.length();
    for (int i = length; i > 0; i--) {
      String key = lookahead.substring(0, i);
      TonePitch pitch = TonePitches.of(key);
      if (pitch != null) {
        in.skip(i);
        return pitch;
      }
    }
    return null;
  }

  @Override
  public void write(TonePitch pitch, MusicOutputStream out, SongFormatContext context) {

    out.write(pitch.getName());
  }
}
