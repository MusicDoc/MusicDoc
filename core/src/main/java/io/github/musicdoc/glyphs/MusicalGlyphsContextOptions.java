package io.github.musicdoc.glyphs;

/**
 * Implementation of {@link MusicalGlyphsOptions} as {@link Enum}.
 */
public enum MusicalGlyphsContextOptions implements MusicalGlyphsOptions {

  /** Default with flags. */
  DEFAULT,

  /** {@link #isOmitFlags() Omit Flags}. */
  NO_FLAGS,

  /** {@link #isNoteheadOnly() Noteheads only}. */
  NOTEHEADS

  ;

  @Override
  public boolean isOmitFlags() {

    return (this == NOTEHEADS) || (this == NO_FLAGS);
  }

  @Override
  public boolean isNoteheadOnly() {

    return this == NOTEHEADS;
  }

}
