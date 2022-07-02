package io.github.musicdoc.music.tone;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsAccidentals;

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
  public TonePitch parse(MusicInputStream chars, SongFormatOptions options) {

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
  public void format(TonePitch pitch, MusicOutputStream out, SongFormatOptions options) {

    out.append(pitch.getName());
  }
}
