package io.github.musicdoc;

import io.github.musicdoc.io.MusicOutputStream;

/**
 * Helper class providing static methods for generic operations on {@link String} and related classes.
 */
public class StringHelper {

  private static final String[] SPACES = new String[] { "", // 0 spaces
  " ", // 1 spaces
  "  ", // 2 spaces
  "   ", // 3 spaces
  "    ", // 4 spaces
  "     ", // 5 spaces
  "      ", // 6 spaces
  "       ", // 7 spaces
  "        " // 8 spaces
  };

  /**
   * @param out the {@link MusicOutputStream} to fill with the given number of spaces.
   * @param count the desired number of spaces.
   */
  public static void appendSpaces(MusicOutputStream out, int count) {

    int delta = count;
    if (delta <= 0) { // already long enough?
      return;
    }
    while (delta >= 8) {
      out.append(SPACES[8]);
      delta = delta - 8;
    }
    out.append(SPACES[delta]);
  }

  /**
   * @param sb the {@link StringBuilder} to fill with the given number of spaces.
   * @param count the desired number of spaces.
   */
  public static void appendSpaces(StringBuilder sb, int count) {

    int delta = count;
    if (delta <= 0) { // already long enough?
      return;
    }
    while (delta >= 8) {
      sb.append(SPACES[8]);
      delta = delta - 8;
    }
    sb.append(SPACES[delta]);
  }

}
