package io.github.musicdoc.music.bar;

import java.util.HashMap;
import java.util.Map;

public class BarType implements BarObject {

  private static final Map<String, BarType> SYMBOL_MAP = new HashMap<>();

  /** A single thin bar. */
  public static final BarType SINGLE = new BarType("|");

  /** A double thin bar. */
  public static final BarType DOUBLE = new BarType("||");

  /** A thick bar followed by a thin bar. */
  public static final BarType THICK_THIN = new BarType("§|");

  /** A thin bar followed by a thick bar. */
  public static final BarType THIN_THICK = new BarType("|§");

  /** A single thick bar. */
  public static final BarType THICK = new BarType("§");

  /** A double thick bar. */
  public static final BarType THICK_THICK = new BarType("§§");

  /** The bar to start a repeat. */
  public static final BarType REPEAT_START = new BarType("|:");

  /** The bar to end a repeat. */
  public static final BarType REPEAT_END = new BarType(":|");

  /** The bar to end a repeat and to start a new repeat. */
  public static final BarType REPEAT_END_START = new BarType("::");

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
  protected BarType(String symbol) {

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
   * @return the according {@link BarType} or {@code null} if no such {@link BarType} could be found.
   */
  public static BarType of(String symbol) {

    return SYMBOL_MAP.get(symbol);
  }
}
