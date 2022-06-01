package io.github.musicdoc.number;

/**
 * Implementation of {@link NumberSet} as a simple range from {@link #getMin() minimum} to {@link #getMax() maximum}
 * (e.g. "1-2", "5-7", or "4" as "4-4").
 */
public class NumberRange implements NumberSet, Comparable<NumberRange> {

  private final int min;

  private final int max;

  private final String text;

  /**
   * The constructor.
   *
   * @param min the {@link #getMin() minimum value}.
   * @param max the {@link #getMax() maximum value}.
   */
  public NumberRange(int min, int max) {

    super();
    this.min = min;
    this.max = max;
    if (min == max) {
      this.text = Integer.toString(min);
    } else {
      this.text = min + "-" + max;
    }
    if (min > max) {
      throw new IllegalArgumentException(this.text);
    }
  }

  /**
   * The constructor.
   *
   * @param value the {@link #getMin() minimum} and {@link #getMax() maximum value}.
   */
  public NumberRange(int value) {

    this(value, value);
  }

  @Override
  public int getMin() {

    return this.min;
  }

  @Override
  public int getMax() {

    return this.max;
  }

  @Override
  public boolean contains(int number) {

    return (number >= this.min) && (number <= this.max);
  }

  @Override
  public int compareTo(NumberRange range) {

    return this.min - range.min;
  }

  /**
   * @param range the {@link NumberRange} to join (build the union with).
   * @return the {@link NumberRange} that {@link #contains(int) contains} exactly those numbers that are
   *         {@link #contains(int) contained} in this or the given {@link NumberRange}. Will be {@code null} if no such
   *         range exists.
   */
  public NumberRange join(NumberRange range) {

    int rMin = range.getMin();
    if (rMin < this.min) {
      return range.join(this);
    }
    if (rMin > this.max + 1) {
      return null;
    }
    // given range can be joined by potentially extending to the right
    int rMax = range.getMax();
    if (rMax <= this.max) {
      return this;
    } else if (rMin == this.min) {
      return range;
    } else {
      return new NumberRange(this.min, rMax);
    }
  }

  @Override
  public String toString() {

    return this.text;
  }

}
