package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.bar.BarType;

/**
 * {@link SmuflGlyphs} for {@link io.github.musicdoc.music.bar.Bar}s.
 */
public interface SmuflGlyphsBar extends SmuflGlyphs {

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

  /** The {@link BarType#REPEAT_START start repeat} symbol: {@value}. */
  String REPEAT_LEFT = "\uE040";

  /** The {@link BarType#REPEAT_END end repeat} symbol: {@value}. */
  String REPEAT_RIGHT = "\uE041";

  /** The {@link BarType#REPEAT_END_START end and start repeat} symbol: {@value}. */
  String REPEAT_RIGHT_LEFT = "\uE040";

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
      return REPEAT_LEFT;
    } else if (barType == BarType.REPEAT_END) {
      return REPEAT_RIGHT;
    } else if (barType == BarType.REPEAT_END_START) {
      return REPEAT_RIGHT_LEFT;
    }
    return null;
  }

}
