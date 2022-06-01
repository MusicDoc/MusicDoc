package io.github.musicdoc.music.glyphs;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.note.StemDirection;

/**
 * Interface for the context to customize {@link MusicalGlyphs#getGlyphs(MusicSymbolContext) unicode symbols}.
 *
 * @see MusicalSymbolContextOptions
 */
public interface MusicSymbolContext {

  /**
   * @return {@code true} to omit flags (e.g. for notes with value of 1/8 or smaller what is useful e.g. for rendering
   *         beams instead of flags or to render chords where stems should be joined), {@code false} otherwise.
   */
  default boolean isOmitFlags() {

    return isNoteheadOnly();
  }

  /**
   * @return {@code true} to get note-heads only and omit slams and flags, {@code false} otherwise. If this method
   *         returns {@code true} also {@link #isOmitFlags()} should return {@code true}.
   */
  default boolean isNoteheadOnly() {

    return false;
  }

  /**
   * @return {@code true} to enforce {@link UnicodeGlyphs}, {@code false} otherwise (for {@link SmuflGlyphs}).
   */
  default boolean isEnforceUnicode() {

    return false;
  }

  /**
   * @return the {@link StemDirection} used to draw stems.
   */
  default StemDirection getStemDirection() {

    return StemDirection.AUTO;
  }

  /**
   * @return the {@link Clef}.
   */
  default Clef getClef() {

    return Clef.TREBLE;
  }

}
