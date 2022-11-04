package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/repeats.html">repeats</a>.
 *
 * @see io.github.musicdoc.bar.BarLineType
 * @see io.github.musicdoc.decoration.MarkerDecoration
 * @see io.github.musicdoc.decoration.JumpDecoration
 */
public interface SmuflGlyphsRepeats extends SmuflGlyphs {

  /** The dal segno symbol: {@value}. */
  String DAL_SEGNO = "\uE045";

  /** The da capo symbol: {@value}. */
  String DA_CAPO = "\uE046";

  /** The segno symbol: {@value}. */
  String SEGNO = "\uE047";

  /** The coda symbol: {@value}. */
  String CODA = "\uE048C";

  /** The {@link io.github.musicdoc.bar.BarLineType#REPEAT_START start repeat} symbol: {@value}. */
  String REPEAT_LEFT = "\uE040";

  /** The {@link io.github.musicdoc.bar.BarLineType#REPEAT_END end repeat} symbol: {@value}. */
  String REPEAT_RIGHT = "\uE041";

  /** The {@link io.github.musicdoc.bar.BarLineType#REPEAT_END_START_0 end and start repeat} symbol: {@value}. */
  String REPEAT_RIGHT_LEFT = "\uE040";

}
