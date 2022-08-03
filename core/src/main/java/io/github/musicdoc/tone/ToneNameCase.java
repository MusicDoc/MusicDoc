package io.github.musicdoc.tone;

import java.util.Locale;

import io.github.musicdoc.tone.pitch.TonePitch;

/**
 * Case-style of a {@link TonePitch#getName() tone name}.
 */
public enum ToneNameCase {

  /** All characters in {@link Character#isLowerCase(char) lower case}. */
  LOWER_CASE,

  /**
   * First character in {@link Character#isUpperCase(char) upper case}, rest {@link Character#isLowerCase(char) lower
   * case}.
   */
  CAPITAL_CASE;

  /**
   * @param name the name to convert to this {@link ToneNameCase}.
   * @return the converted name.
   */
  public String convert(String name) {

    if ((name == null) || name.isEmpty()) {
      return name;
    }
    switch (this) {
      case LOWER_CASE:
        return name.toLowerCase(Locale.US);
      case CAPITAL_CASE:
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase(Locale.US);
    }
    return name;
  }
}
