package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/beams-and-slurs.html">beams and slurs</a>.
 */
public interface SmuflGlyphsBeams extends SmuflGlyphs {

  /** The begin beam symbol: {@value}. */
  String BEAM_START = "\uE8E0";

  /** The end beam symbol: {@value}. */
  String BEAM_END = "\uE8E1";

  /** The begin tie symbol: {@value}. */
  String TIE_START = "\uE8E2";

  /** The end tie symbol: {@value}. */
  String TIE_END = "\uE8E3";

  /** The begin slur symbol: {@value}. */
  String SLUR_START = "\uE8E4";

  /** The end slur symbol: {@value}. */
  String SLUR_END = "\uE8E5";

  /** The begin phrase symbol: {@value}. */
  String PHRASE_START = "\uE8E6";

  /** The end phrase symbol: {@value}. */
  String PHRASE_END = "\uE8E7";

}
