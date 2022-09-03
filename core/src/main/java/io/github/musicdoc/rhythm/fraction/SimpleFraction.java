package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;

/**
 * Extends {@link AbstractFraction} for simple fractions that can never have a {@link #getVariation() variation}.
 *
 * @param <T> type of this class itself.
 */
public abstract class SimpleFraction<T extends SimpleFraction<T>> extends AbstractFraction<T> {

  /** @see #getBeats() */
  protected int beats;

  /** @see #getUnit() */
  protected int unit;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   */
  public SimpleFraction(int beats, int unit) {

    super();
    if (beats < 0) {
      throw new IllegalArgumentException("Invalid beats value " + beats + " - must be positive!");
    }
    if (unit < 1) {
      throw new IllegalArgumentException("Invalid unit value " + unit + " - must be positive!");
    }
    this.beats = beats;
    this.unit = unit;
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   */
  public SimpleFraction(Fraction fraction) {

    this(fraction.getBeats(), fraction.getUnit());
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected SimpleFraction(SimpleFraction<T> fraction, MutableObjecteCopier copier) {

    super(fraction, copier);
    this.beats = fraction.beats;
    this.unit = fraction.unit;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  /**
   * @param beats the new value of {@link #getBeats()}.
   * @return a new {@link Fraction} with the given {@link #getBeats() beats} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public T setBeats(int beats) {

    if (this.beats == beats) {
      return self();
    }
    T fract = makeMutable();
    fract.beats = beats;
    return fract;
  }

  @Override
  public int getUnit() {

    return this.unit;
  }

  /**
   * @param unit the new value of {@link #getUnit()}.
   * @return a new {@link Fraction} with the given {@link #getUnit() unit} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public T setUnit(int unit) {

    if (this.unit == unit) {
      return self();
    }
    T fract = makeMutable();
    fract.unit = unit;
    return fract;
  }

  /**
   * @param fract the {@link Fraction} to multiply with.
   * @return the product of this {@link Fraction} times the given {@link Fraction}.
   */
  public T multiply(Fraction fract) {

    fract = fract.normalize();
    return normalize(this.beats * fract.getBeats(), this.unit * fract.getUnit());
  }

  /**
   * @param fract the {@link Fraction} to divide by.
   * @return the quotient of this {@link Fraction} as numerator divided by the given {@link Fraction} as denominator.
   */
  public T divide(Fraction fract) {

    fract = fract.normalize();
    return normalize(this.beats * fract.getUnit(), this.unit * fract.getBeats());
  }

  @Override
  public T normalize() {

    return normalize(this.beats, this.unit);
  }

  private T normalize(int beat, int unt) {

    while ((beat != 1) && (unt != 1)) {
      if (((beat % 2) == 0) && (unt % 2) == 0) {
        beat /= 2;
        unt /= 2;
      } else if (((beat % 3) == 0) && (unt % 3) == 0) {
        beat /= 3;
        unt /= 3;
      } else {
        break;
      }
    }
    return setBeats(beat).setUnit(unt);
  }

  // for documentation and enforcement

  @Override
  public final MusicalValueVariation getVariation() {

    return MusicalValueVariation.NONE;
  }

  @Override
  public SimpleFraction<?> getPlain() {

    return this;
  }

}
