package io.github.musicdoc.music.glyphs;

/**
 * TODO joerg This type ...
 *
 */
public enum MusicalSymbolContextOptions implements MusicSymbolContext {

  DEFAULT,

  NO_FLAGS,

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
