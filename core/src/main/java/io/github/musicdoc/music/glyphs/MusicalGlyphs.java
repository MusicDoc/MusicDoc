package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.MusicalObject;

/**
 * Interface for a musical element that can be represented as {@link #getGlyphs() unicode symbol}.
 */
public interface MusicalGlyphs extends MusicalObject {

  /**
   * @return the glyph symbol(s) to render this musical element using a
   *         <a href="https://w3c.github.io/smufl/latest/index.html">SMuFL</a> font.
   */
  default String getGlyphs() {

    return getGlyphs(MusicalGlyphsContext.DEFAULT);
  }

  /**
   * @param context the {@link MusicalGlyphsContext} to customize the result.
   * @return the unicode symbol(s) to render this musical element using a
   *         <a href="https://w3c.github.io/smufl/latest/index.html">SMuFL</a> font.
   */
  String getGlyphs(MusicalGlyphsContext context);

}
