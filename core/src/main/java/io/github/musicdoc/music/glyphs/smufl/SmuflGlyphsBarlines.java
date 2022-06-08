package io.github.musicdoc.music.glyphs.smufl;

import io.github.musicdoc.music.bar.BarType;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/barlines.html">barlines</a>.
 *
 * @see io.github.musicdoc.music.bar.Bar
 */
public interface SmuflGlyphsBarlines extends SmuflGlyphs {

  /** The {@link BarType#SINGLE single bar} symbol: {@value}. */
  String BAR_SINGLE = "\uE030";

  /** The {@link BarType#DOUBLE double bar} symbol: {@value}. */
  String BAR_DOUBLE = "\uE031";

  /** The {@link BarType#THIN_THICK final bar} symbol: {@value}. */
  String BAR_FINAL = "\uE032";

  /** The {@link BarType#THICK_THIN reverse final bar} symbol: {@value}. */
  String BAR_REVERSE_FINAL = "\uE033";

  /** The {@link BarType#THICK heavy bar} symbol: {@value}. */
  String BAR_HEAVY_SINGLE = "\uE034";

  /** The {@link BarType#THICK_THICK double heavy bar} symbol: {@value}. */
  String BAR_HEAVY_DOUBLE = "\uE034";

  /**
   * @param barType the {@link BarType}.
   * @return the according glyph.
   */
  static String get(BarType barType) {

    if (barType == BarType.SINGLE) {
      return BAR_SINGLE;
    } else if (barType == BarType.DOUBLE) {
      return BAR_DOUBLE;
    } else if (barType == BarType.THIN_THICK) {
      return BAR_FINAL;
    } else if (barType == BarType.THICK) {
      return BAR_HEAVY_SINGLE;
    } else if (barType == BarType.THICK_THIN) {
      return BAR_REVERSE_FINAL;
    } else if (barType == BarType.THICK_THICK) {
      return BAR_HEAVY_DOUBLE;
    } else if (barType == BarType.REPEAT_START) {
      return SmuflGlyphsRepeats.REPEAT_LEFT;
    } else if (barType == BarType.REPEAT_END) {
      return SmuflGlyphsRepeats.REPEAT_RIGHT;
    } else if (barType == BarType.REPEAT_END_START) {
      return SmuflGlyphsRepeats.REPEAT_RIGHT_LEFT;
    }
    return null;
  }

}
