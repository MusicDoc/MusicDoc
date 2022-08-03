package io.github.musicdoc.decoration;

/**
 * {@link Enum} defining the positions of a valued item.
 */
public enum MusicalDecorationPosition {

  /** On top of the {@link io.github.musicdoc.music.stave.Stave}. */
  TOP,

  /** At the bottom of the {@link io.github.musicdoc.music.stave.Stave}. */
  BOTTOM,

  /** Above the {@link io.github.musicdoc.music.rythm.value.ValuedItem item}. */
  ABOVE,

  /** Below the {@link io.github.musicdoc.music.rythm.value.ValuedItem item}. */
  BELOW,

  /**
   * Above or below the {@link io.github.musicdoc.music.note.StemDirection stem}(s) of the
   * {@link io.github.musicdoc.music.rythm.value.ValuedItem item}(s). So it follows the
   * {@link io.github.musicdoc.music.note.StemDirection} and is placed at the vertical end of the stem.
   */
  STEM,

  /**
   * Above or below the note-head(s) of the {@link io.github.musicdoc.music.rythm.value.ValuedItem item}(s) so a
   * {@link io.github.musicdoc.music.note.StemDirection stem} does not cross the decoration. Therefore it is opposite to
   * the {@link io.github.musicdoc.music.note.StemDirection}.
   */
  NOTEHEAD,

  /** Left to the {@link io.github.musicdoc.music.rythm.value.ValuedItem item}. */
  LEFT,

  /** Rigth to the {@link io.github.musicdoc.music.rythm.value.ValuedItem item}. */
  RIGHT
}
