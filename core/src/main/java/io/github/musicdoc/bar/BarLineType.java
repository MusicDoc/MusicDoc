package io.github.musicdoc.bar;

import io.github.musicdoc.MusicalObject;

/**
 * {@link BarLine#getType() Type} of a {@link BarLine}.
 */
public enum BarLineType implements MusicalObject {

  /** A double thick bar. */
  THICK_THICK("|*|*"),

  /** A thick bar followed by a thin bar. */
  THICK_THIN("|*|"),

  /** A thin bar followed by a thick bar. */
  THIN_THICK("||*"),

  /** A single thick bar. */
  THICK("|*"),

  /** A double thin bar. */
  DOUBLE("||"),

  /** The bar to end a repeat and to start a new repeat (short form without '|'). */
  REPEAT_END_START_0("::"),

  /** The bar to end a repeat and to start a new repeat (form with single '|'). */
  REPEAT_END_START_1(":|:"),

  /** The bar to end a repeat and to start a new repeat (form with double '|'). */
  REPEAT_END_START_2(":||:"),

  /** The bar to start a repeat. */
  REPEAT_START("|:"),

  /** The bar to end a repeat. */
  REPEAT_END(":|"),

  /** A single thin bar. */
  SINGLE("|");

  private final String syntax;

  private BarLineType(String symbol) {

    this.syntax = symbol;
  }

  /**
   * @return {@code true} for a repeat type ({@link #REPEAT_START}, {@link #REPEAT_END}, {@link #REPEAT_END_START_0},
   *         {@link #REPEAT_END_START_1}, {@link #REPEAT_END_START_2}), {@code false} otherwise.
   */
  public boolean isRepeat() {

    return (this == REPEAT_START) || (this == REPEAT_END) || (this == REPEAT_END_START_0)
        || (this == REPEAT_END_START_1) || (this == REPEAT_END_START_2);
  }

  /**
   * @return {@code true} for a repeat end+start type ({@link #REPEAT_END_START_0}, {@link #REPEAT_END_START_1},
   *         {@link #REPEAT_END_START_2}), {@code false} otherwise.
   */
  public boolean isRepeatEndStart() {

    return (this == REPEAT_END_START_0) || (this == REPEAT_END_START_1) || (this == REPEAT_END_START_2);
  }

  /**
   * @return the notation symbol if this bar type.
   */
  public String getSyntax() {

    return this.syntax;
  }

  @Override
  public String toString() {

    return this.syntax;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.syntax);
  }

  /**
   * @param syntax the {@link #getSyntax() syntax}.
   * @return the {@link BarLineType} with the given {@link #getSyntax() syntax} or {@code null} if no such
   *         {@link BarLineType} could be found.
   */
  public static BarLineType of(String syntax) {

    for (BarLineType type : values()) {
      if (type.syntax.equals(syntax)) {
        return type;
      }
    }
    return null;
  }
}
