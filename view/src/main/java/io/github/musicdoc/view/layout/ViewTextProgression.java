package io.github.musicdoc.view.layout;

/**
 * Enumeration with the available options how to progress position when text is rendered.
 */
public enum ViewTextProgression {

  /** Keep current position unchanged. */
  NONE,

  /** Advance position horizontally after the text. */
  H_STEP,

  /** Advance position vertically below the text. */
  V_STEP,

  /** Advance position vertically below the text and horizontally to the beginning of the line. */
  NEWLINE,

  /** Ignore current position and place absolute to top-left corner. */
  ABSOLUTE;

  /**
   * @return {@code true} to advance position horizontally after the text, {@code false} otherwise.
   */
  public boolean isHorizontal() {

    return (this == H_STEP) || (this == NEWLINE);
  }

  /**
   * @return {@code true} to advance position vertically below the text, {@code false} otherwise.
   */
  public boolean isVertical() {

    return (this == V_STEP) || (this == NEWLINE);
  }

}
