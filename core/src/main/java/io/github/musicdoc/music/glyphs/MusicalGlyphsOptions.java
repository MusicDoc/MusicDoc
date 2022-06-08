package io.github.musicdoc.music.glyphs;

/**
 * Interface for options of {@link MusicalGlyphsContext}.
 *
 * @see MusicalGlyphsContextOptions
 */
public interface MusicalGlyphsOptions {

  /**
   * @return {@code true} to omit flags (e.g. for notes with value of 1/8 or smaller what is useful e.g. for rendering
   *         beams instead of flags or to render chords where stems should be joined), {@code false} otherwise.
   */
  boolean isOmitFlags();

  /**
   * @return {@code true} to get note-heads only and omit stems and flags, {@code false} otherwise. If this method
   *         returns {@code true} also {@link #isOmitFlags()} should return {@code true}.
   */
  boolean isNoteheadOnly();

}
