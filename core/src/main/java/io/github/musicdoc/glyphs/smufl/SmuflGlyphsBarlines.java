package io.github.musicdoc.glyphs.smufl;

import io.github.musicdoc.bar.BarLineType;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/barlines.html">barlines</a>.
 *
 * @see io.github.musicdoc.bar.BarLine
 */
public interface SmuflGlyphsBarlines extends SmuflGlyphs {

  /** The {@link BarLineType#SINGLE single bar} symbol: {@value}. */
  String BAR_SINGLE = "\uE030";

  /** The {@link BarLineType#DOUBLE double bar} symbol: {@value}. */
  String BAR_DOUBLE = "\uE031";

  /** The {@link BarLineType#THIN_THICK final bar} symbol: {@value}. */
  String BAR_FINAL = "\uE032";

  /** The {@link BarLineType#THICK_THIN reverse final bar} symbol: {@value}. */
  String BAR_REVERSE_FINAL = "\uE033";

  /** The {@link BarLineType#THICK heavy bar} symbol: {@value}. */
  String BAR_HEAVY_SINGLE = "\uE034";

  /** The {@link BarLineType#THICK_THICK double heavy bar} symbol: {@value}. */
  String BAR_HEAVY_DOUBLE = "\uE034";

  /**
   * @param barType the {@link BarLineType}.
   * @return the according glyph.
   */
  static String get(BarLineType barType) {

    if (barType == null) {
      return null;
    } else if (barType == BarLineType.SINGLE) {
      return BAR_SINGLE;
    } else if (barType == BarLineType.DOUBLE) {
      return BAR_DOUBLE;
    } else if (barType == BarLineType.THIN_THICK) {
      return BAR_FINAL;
    } else if (barType == BarLineType.THICK) {
      return BAR_HEAVY_SINGLE;
    } else if (barType == BarLineType.THICK_THIN) {
      return BAR_REVERSE_FINAL;
    } else if (barType == BarLineType.THICK_THICK) {
      return BAR_HEAVY_DOUBLE;
    } else if (barType == BarLineType.REPEAT_START) {
      return SmuflGlyphsRepeats.REPEAT_LEFT;
    } else if (barType == BarLineType.REPEAT_END) {
      return SmuflGlyphsRepeats.REPEAT_RIGHT;
    } else if (barType.isRepeatEndStart()) {
      return SmuflGlyphsRepeats.REPEAT_RIGHT_LEFT;
    }
    return null;
  }

}
