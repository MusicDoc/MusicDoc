package io.github.musicdoc.number;

import java.util.Arrays;
import java.util.Objects;

/**
 * Implementation of {@link NumberSet} that represents the union of one or multiple {@link NumberRange}s.
 */
public class NumberRangeSet implements NumberSet {

  private final NumberRange[] ranges;

  private final String text;

  private NumberRangeSet(NumberRange... ranges) {

    super();
    this.ranges = ranges;
    StringBuilder sb = new StringBuilder();
    for (NumberRange range : ranges) {
      if (sb.length() > 0) {
        sb.append(",");
      }
      sb.append(range);
    }
    this.text = sb.toString();
  }

  @Override
  public int getMin() {

    return this.ranges[0].getMin();
  }

  @Override
  public int getMax() {

    return this.ranges[this.ranges.length - 1].getMax();
  }

  @Override
  public boolean contains(int number) {

    for (NumberRange range : this.ranges) {
      if (range.contains(number)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {

    return this.text;
  }

  public static NumberRangeSet of(NumberRange... ranges) {

    Objects.requireNonNull(ranges, "ranges");
    if (ranges.length == 0) {
      throw new IllegalArgumentException("ranges must not be empty");
    } else if (ranges.length > 1) {
      Arrays.sort(ranges);
    }
    int readIndex = 0;
    int writeIndex = 0;
    NumberRange range = ranges[readIndex++];
    while (readIndex < ranges.length) {
      NumberRange joined = range.join(ranges[readIndex++]);
      if (joined == null) {
        writeIndex++;
      } else {
        ranges[writeIndex] = joined;
        range = joined;
      }
    }
    writeIndex++;
    if (writeIndex < ranges.length) {
      ranges = Arrays.copyOf(ranges, writeIndex);
    }
    return new NumberRangeSet(ranges);
  }
  //
  // public static NumberRangeSet of(String ranges) {
  //
  // Objects.requireNonNull(ranges, "ranges");
  // int i = 0;
  // int len = ranges.length();
  // while (i < len) {
  // char c = ranges.charAt(i++);
  //
  // }
  // }

}
