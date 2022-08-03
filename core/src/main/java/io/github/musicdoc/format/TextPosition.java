package io.github.musicdoc.format;

/**
 * Interface for an object that has a position in a text with {@link #getLine() line} and {@link #getColumn() column}
 * number.
 */
public interface TextPosition {

  /**
   * @return the line number starting from {@code 1} for the first line.
   */
  int getLine();

  /**
   * @return the column number starting from {@code 1} for the first character in the {@link #getLine() line}.
   */
  int getColumn();
}
