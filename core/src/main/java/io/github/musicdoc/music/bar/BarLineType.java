package io.github.musicdoc.music.bar;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link BarLine#getType() Type} of a {@link BarLine}.
 */
public class BarLineType implements BarLineObject {

  private static final Map<String, BarLineType> SYMBOL_MAP = new HashMap<>();

  /** A single thin bar. */
  public static final BarLineType SINGLE = new BarLineType("|");

  /** A double thin bar. */
  public static final BarLineType DOUBLE = new BarLineType("||");

  /** A thick bar followed by a thin bar. */
  public static final BarLineType THICK_THIN = new BarLineType("§|");

  /** A thin bar followed by a thick bar. */
  public static final BarLineType THIN_THICK = new BarLineType("|§");

  /** A single thick bar. */
  public static final BarLineType THICK = new BarLineType("§");

  /** A double thick bar. */
  public static final BarLineType THICK_THICK = new BarLineType("§§");

  /** The bar to start a repeat. */
  public static final BarLineType REPEAT_START = new BarLineType("|:");

  /** The bar to end a repeat. */
  public static final BarLineType REPEAT_END = new BarLineType(":|");

  /** The bar to end a repeat and to start a new repeat. */
  public static final BarLineType REPEAT_END_START = new BarLineType("::");

  private final String symbol;

  static {
    SYMBOL_MAP.put(":||:", REPEAT_END_START);
    SYMBOL_MAP.put(":|:", REPEAT_END_START);
  }

  /**
   * The constructor.
   *
   * @param symbol the {@link #getSymbol() symbol}.
   */
  protected BarLineType(String symbol) {

    super();
    this.symbol = symbol;
    SYMBOL_MAP.put(symbol, this);
  }

  @Override
  public boolean isLeftBarOnly() {

    return (this == REPEAT_START);
  }

  @Override
  public boolean isRightBarOnly() {

    return (this == REPEAT_END);
  }

  /**
   * @return {@code true} for a repeat type ({@link #REPEAT_START}, {@link #REPEAT_END}, {@link #REPEAT_END_START}),
   *         {@code false} otherwise.
   */
  public boolean isRepeat() {

    return (this == REPEAT_START) || (this == REPEAT_END) || (this == REPEAT_END_START);
  }

  /**
   * @return the notation symbol if this bar type.
   */
  public String getSymbol() {

    return this.symbol;
  }

  @Override
  public String toString() {

    return this.symbol;
  }

  /**
   * @param symbol the symbol to parse.
   * @return the according {@link BarLineType} or {@code null} if no such {@link BarLineType} could be found.
   */
  public static BarLineType of(String symbol) {

    return SYMBOL_MAP.get(symbol);
  }
}
