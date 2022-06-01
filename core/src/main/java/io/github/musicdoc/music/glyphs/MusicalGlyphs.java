package io.github.musicdoc.music.glyphs;

/**
 * Interface for a musical element that can be represented as {@link #getGlyphs() unicode symbol}.
 */
public interface MusicalGlyphs {

  /**
   * @return the unicode symbol(s) to render this musical element using a
   *         <a href="https://w3c.github.io/smufl/latest/index.html">SMuFL</a> font.
   */
  default String getGlyphs() {

    return getGlyphs(MusicalSymbolContextOptions.DEFAULT);
  }

  /**
   * @param context the {@link MusicSymbolContext} to customize the result.
   * @return the unicode symbol(s) to render this musical element using a
   *         <a href="https://w3c.github.io/smufl/latest/index.html">SMuFL</a> font.
   */
  String getGlyphs(MusicSymbolContext context);

}
