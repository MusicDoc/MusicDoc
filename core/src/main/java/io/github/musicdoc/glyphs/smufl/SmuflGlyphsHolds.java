package io.github.musicdoc.glyphs.smufl;

/**
 * {@link SmuflGlyphs} for <a href="https://w3c.github.io/smufl/latest/tables/holds-and-pauses.html">holds and
 * pauses</a>.
 */
public interface SmuflGlyphsHolds extends SmuflGlyphs {

  /** The fermata above symbol: {@value}. */
  String FERMATA_ABOVE = "\uE4C0";

  /** The fermata below symbol: {@value}. */
  String FERMATA_BELOW = "\uE4C1";

  /** The breath symbol: {@value}. */
  String BREATH_MARK = "\uE4CE";

  /** The caesura symbol: {@value}. */
  String CAESURA = "\uE4D1";
}
