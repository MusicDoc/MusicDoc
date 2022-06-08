package io.github.musicdoc.music.tone;

import java.io.IOException;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsAccidentals;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link TonePitch}.
 */
public class TonePitchMapper extends AbstractMapper<TonePitch> {

  /** The singleton instance. */
  public static final TonePitchMapper INSTANCE = new TonePitchMapper();

  /** {@link CharFilter} that {@link CharFilter#accept(char) accepts} start characters of a {@link TonePitch}. */
  public static final ListCharFilter FILTER_TONE_START = ListCharFilter.allOfAnyCase("ABCDEFGH");

  /** {@link CharFilter} that {@link CharFilter#accept(char) accepts} start characters of a {@link TonePitch}. */
  public static final ListCharFilter FILTER_TONE = FILTER_TONE_START.join('i', 'I', 's', 'S', '#', 'b',
      UnicodeGlyphsAccidentals.NEUTRAL_CHAR, UnicodeGlyphsAccidentals.FLAT_1_CHAR, UnicodeGlyphsAccidentals.SHARP_1_CHAR,
      UnicodeGlyphsAccidentals.SIGN_2_CHAR1, UnicodeGlyphsAccidentals.FLAT_2_CHAR2,
      UnicodeGlyphsAccidentals.SHARP_2_CHAR2);

  @Override
  public TonePitch parse(CharStream chars) {

    char c = chars.peek();
    if (!FILTER_TONE_START.accept(c)) {
      return null;
    }
    String lookahead = chars.peekWhile(FILTER_TONE, 5); // maximum length of TonePitch.name is 5 (e.g. "Cisis")
    int length = lookahead.length();
    for (int i = length; i > 0; i--) {
      String key = lookahead.substring(0, i);
      TonePitch pitch = TonePitches.of(key);
      if (pitch != null) {
        chars.skip(i);
        return pitch;
      }
    }
    return null;
  }

  @Override
  public void format(TonePitch pitch, Appendable buffer, SongFormatOptions options) throws IOException {

    buffer.append(pitch.getName());
  }
}
