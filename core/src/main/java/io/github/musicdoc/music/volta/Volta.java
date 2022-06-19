package io.github.musicdoc.music.volta;

import java.util.Objects;

import io.github.musicdoc.number.NumberSet;

/**
 * A {@link Volta} specifies an ending of a repeat. This is often called <em>volta bracket</em> or <em>time bars</em>.
 * It is visualized as horizontal bracket above the stave containing {@link #getNumbers() numbers} explaining when the
 * {@link Volta} is played.
 */
public class Volta {

  /** The empty {@link Volta}. */
  public static final Volta NONE = new Volta(NumberSet.EMPTY, 0);

  private final NumberSet numbers;

  private final int extraBars;

  /**
   * The constructor.
   *
   * @param numbers the {@link #getNumbers() numbers}.
   * @param extraBars the {@link #getExtraBars() number of extra bars to span}.
   */
  private Volta(NumberSet numbers, int extraBars) {

    super();
    this.numbers = numbers;
    this.extraBars = extraBars;
  }

  /**
   * @return the {@link NumberSet}.
   */
  public NumberSet getNumbers() {

    return this.numbers;
  }

  /**
   * @return the number of extra bars this {@link Volta} exceeds. Default value is {@code 0} meaning this {@link Volta}
   *         will only span the current bar.
   */
  public int getExtraBars() {

    return this.extraBars;
  }

  /**
   * @param numbers the {@link #getNumbers() numbers}.
   * @return the specified {@link Volta}.
   */
  public static Volta of(NumberSet numbers) {

    return of(numbers, 0);
  }

  /**
   * @param numbers the {@link #getNumbers() numbers}.
   * @param extraBars the {@link #getExtraBars() extra bars}.
   * @return the specified {@link Volta}.
   */
  public static Volta of(NumberSet numbers, int extraBars) {

    Objects.requireNonNull(numbers);
    if ((numbers == NumberSet.EMPTY) && (extraBars == 0)) {
      return NONE;
    }
    if (numbers.getMin() < 1) {
      throw new IllegalArgumentException("" + numbers);
    }
    if (extraBars < 0) {
      throw new IllegalArgumentException(Integer.toString(extraBars));
    }
    return new Volta(numbers, extraBars);
  }

}
