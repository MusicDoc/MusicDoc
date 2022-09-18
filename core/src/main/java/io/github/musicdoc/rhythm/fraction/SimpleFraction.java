package io.github.musicdoc.rhythm.fraction;

import java.util.Objects;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;

/**
 * Simple implementation of {@link Fraction} for {@link #getPlain() plain} fraction.
 *
 * @param <SELF> type of this class itself.
 */
public abstract class SimpleFraction<SELF extends SimpleFraction<SELF>> extends AbstractMusicalObject
    implements Fraction, MutableObject<SELF> {

  /** @see #isImmutable() */
  protected boolean immutable;

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
  protected SimpleFraction(SimpleFraction<SELF> fraction, MutableObjecteCopier copier) {

    super();
    this.beats = fraction.beats;
    this.unit = fraction.unit;
  }

  /**
   * @return the explicit {@link #toString() string representation} of the {@link #getPlain() plain fraction} or
   *         {@code null} to use the default ({@link #getBeats()} + "/" + {@link #getUnit()}).
   */
  protected String getText() {

    return null;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public SELF makeImmutable() {

    this.immutable = true;
    return self();
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
  public SELF setBeats(int beats) {

    if (this.beats == beats) {
      return self();
    }
    SELF fract = makeMutable();
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
  public SELF setUnit(int unit) {

    if (this.unit == unit) {
      return self();
    }
    SELF fract = makeMutable();
    fract.unit = unit;
    return fract;
  }

  /**
   * @param fract the {@link Fraction} to multiply with.
   * @return the product of this {@link Fraction} times the given {@link Fraction}.
   */
  public SELF multiply(Fraction fract) {

    fract = fract.normalize();
    return normalize(this.beats * fract.getBeats(), this.unit * fract.getUnit());
  }

  /**
   * @param fract the {@link Fraction} to divide by.
   * @return the quotient of this {@link Fraction} as numerator divided by the given {@link Fraction} as denominator.
   */
  public SELF divide(Fraction fract) {

    fract = fract.normalize();
    return normalize(this.beats * fract.getUnit(), this.unit * fract.getBeats());
  }

  @Override
  public SELF normalize() {

    return normalize(this.beats, this.unit);
  }

  private SELF normalize(int beat, int unt) {

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

  @Override
  public SimpleFraction<?> getPlain() {

    return this;
  }

  @Override
  public int hashCode() {

    return 31 * getBeats() + getUnit();
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    SimpleFraction<?> other = (SimpleFraction<?>) obj;
    if (this.beats != other.beats) {
      return false;
    } else if (this.unit != other.unit) {
      return false;
    } else if (!Objects.equals(getText(), other.getText())) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    String text = getText();
    if (text == null) {
      sb.append(this.beats);
      sb.append("/");
      sb.append(this.unit);
    } else {
      sb.append(text);
    }
  }

}
