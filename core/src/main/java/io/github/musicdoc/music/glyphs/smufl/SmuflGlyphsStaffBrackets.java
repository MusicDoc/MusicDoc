package io.github.musicdoc.music.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/barlines.html">barlines</a>.
 *
 * @see io.github.musicdoc.music.bar.BarLine
 */
public interface SmuflGlyphsStaffBrackets extends SmuflGlyphs {

  /** The {@link io.github.musicdoc.music.stave.StaveBracket#CURLY curly brace} symbol: {@value}. */
  String BRACE = "\uE000";

  /** The reversed {@link #BRACE} symbol: {@value}. */
  String REVERSED_BRACE = "\uE001";

  /** The {@link io.github.musicdoc.music.stave.StaveBracket#SQUARE square bracket} symbol: {@value}. */
  String BRACKET = "\uE002";

}
