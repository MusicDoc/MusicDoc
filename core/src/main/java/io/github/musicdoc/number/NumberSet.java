package io.github.musicdoc.number;

/**
 * Interface for a set of numbers (e.g. "1", "1-3", or "1,3-4,7").
 */
public interface NumberSet {

  /** The empty {@link NumberSet}. */
  NumberSet EMPTY = EmptyNumberSet.INSTANCE;

  /**
   * @return the minimum number {@link #contains(int) contained} in this set.
   */
  int getMin();

  /**
   * @return the maximum number {@link #contains(int) contained} in this set.
   */
  int getMax();

  /**
   * @param number the number to check.
   * @return {@code true} if the given number is contained in this set, {@code false} otherwise.
   */
  boolean contains(int number);

  @Override
  String toString();

}
