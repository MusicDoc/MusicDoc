package io.github.musicdoc.music.glyphs.smufl;

import io.github.musicdoc.music.bar.BarType;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphs;

/**
 * {@link UnicodeGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/repeats.html">repeats</a>.
 *
 * @see io.github.musicdoc.music.decoration.MarkerDecoration
 * @see io.github.musicdoc.music.decoration.JumpDecoration
 */
public interface SmuflGlyphsRepeats extends UnicodeGlyphs {

  /** The dal segno symbol: {@value}. */
  String DAL_SEGNO = "\uE045";

  /** The da capo symbol: {@value}. */
  String DA_CAPO = "\uE046";

  /** The segno symbol: {@value}. */
  String SEGNO = "\uE047";

  /** The coda symbol: {@value}. */
  String CODA = "\uE048C";

  /** The {@link BarType#REPEAT_START start repeat} symbol: {@value}. */
  String REPEAT_LEFT = "\uE040";

  /** The {@link BarType#REPEAT_END end repeat} symbol: {@value}. */
  String REPEAT_RIGHT = "\uE041";

  /** The {@link BarType#REPEAT_END_START end and start repeat} symbol: {@value}. */
  String REPEAT_RIGHT_LEFT = "\uE040";

}
