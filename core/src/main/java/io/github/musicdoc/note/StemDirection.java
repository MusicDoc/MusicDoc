package io.github.musicdoc.note;

/**
 * {@link Enum} with the possible types for the direction of a <em>steam</em>. A stem is the line connected to a
 * note-head if its value is less than a whole tone indicating its value (optionally using flags or bars).
 */
public enum StemDirection {

  /** Automatically determine the stem direction from the {@link io.github.musicdoc.tone.Tone}. */
  AUTO,

  /** Always draw the stem up from the note-head towards the top of the score. */
  UP,

  /** Always draw the stem down from the note-head towards the bottom of the score. */
  DOWN;

  /**
   * @param stemDirection the {@link StemDirection} to check.
   * @return {@code true} if the given {@link StemDirection} is {@link #AUTO} or {@code null}, {@code false} otherwise.
   */
  public static boolean isAuto(StemDirection stemDirection) {

    return (stemDirection == null) || (stemDirection == AUTO);
  }

}
