package io.github.musicdoc.volta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.number.NumberRange;
import io.github.musicdoc.number.NumberRangeSet;
import io.github.musicdoc.number.NumberSet;

/**
 * {@link AbstractMapper Mapper} for {@link Volta}.
 */
public abstract class VoltaMapper extends AbstractMapper<Volta> {

  private static final char EXTRA_BARS_CHAR = '+';

  private static final char RANGE_CHAR = '-';

  private static final char SET_CHAR = ',';

  /**
   * The constructor.
   */
  protected VoltaMapper() {

    super();
  }

  @Override
  public void write(Volta volta, MusicOutputStream out, SongFormatContext context) {

    if ((volta == null) || (volta == Volta.NONE)) {
      return;
    }
    NumberSet numbers = volta.getNumbers();
    out.write(numbers.toString());
    int extraBars = volta.getExtraBars();
    if (extraBars > 0) {
      out.write(EXTRA_BARS_CHAR);
      out.write(Integer.toString(extraBars));
    }
  }

  @Override
  public Volta read(MusicInputStream in, SongFormatContext context) {

    NumberSet numbers = parseSet(in);
    if (numbers == null) {
      return Volta.NONE;
    }
    int extraBars = 0;
    if (in.expect(EXTRA_BARS_CHAR)) {
      Integer i = in.readInteger(3, false);
      if (i == null) {
        in.addError("Volta extra bars (+) has to be followed by a number.");
      } else {
        extraBars = i.intValue();
      }
    }
    return Volta.of(numbers, extraBars);
  }

  private static NumberSet parseSet(MusicInputStream chars) {

    NumberRange range = parseRange(chars);
    if (range == null) {
      return null;
    }
    NumberSet numbers = range;
    List<NumberRange> ranges = null;
    while (chars.expect(SET_CHAR)) {
      if (ranges == null) {
        ranges = new ArrayList<>();
        ranges.add(range);
      }
      range = parseRange(chars);
      if (range == null) {
        chars.addError("Volta set is missing range.");
      } else {
        ranges.add(range);
      }
    }
    if (ranges != null) {
      Collections.sort(ranges);
      numbers = NumberRangeSet.of(ranges.toArray(new NumberRange[ranges.size()]));
    }
    return numbers;
  }

  private static NumberRange parseRange(MusicInputStream chars) {

    Integer i = chars.readInteger(2, false);
    if (i == null) {
      return null;
    }
    int min = i.intValue();
    int max = min;
    if (chars.expect(RANGE_CHAR)) {
      i = chars.readInteger(2, false);
      if (i == null) {
        chars.addError("Volta range is missing max value.");
      } else {
        max = i.intValue();
      }
    }
    return new NumberRange(min, max);
  }

}
