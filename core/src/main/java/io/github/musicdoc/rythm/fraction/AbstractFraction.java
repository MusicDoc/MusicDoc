package io.github.musicdoc.rythm.fraction;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;

/**
 * Abstract base implementation of {@link Fraction}.
 *
 * @param <T> type of this class itself.
 */
public abstract class AbstractFraction<T extends AbstractFraction<T>> extends AbstractMusicalObject
    implements Fraction, MutableObject<T> {

  /** @see #getBeats() */
  protected int beats;

  /** @see #getUnit() */
  protected int unit;

  private String text;

  /** @see #isImmutable() */
  protected boolean immutable;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param fraction the {@link #getUnit() unit}.
   */
  public AbstractFraction(int beats, int fraction) {

    this(beats, fraction, null);
  }

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param text the explicit {@link #getText()} or {@code null}.
   */
  public AbstractFraction(int beats, int unit, String text) {

    super();
    if (beats < 0) {
      throw new IllegalArgumentException("Invalid beats value " + beats + " - must be positive!");
    }
    if (unit < 1) {
      throw new IllegalArgumentException("Invalid fraction value " + unit + " - must be positive!");
    }
    this.beats = beats;
    this.unit = unit;
    this.text = text;
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   */
  public AbstractFraction(Fraction fraction) {

    this(fraction, fraction.normalize());
  }

  private AbstractFraction(Fraction fraction, Fraction normalized) {

    this(normalized.getBeats(), normalized.getUnit());
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AbstractFraction(AbstractFraction<T> fraction, MutableObjecteCopier copier) {

    this(fraction.beats, fraction.unit, fraction.text);
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
    // cast to eclipse compiler bug
    ((AbstractFraction<?>) fract).text = null;
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
    // cast to eclipse compiler bug
    ((AbstractFraction<?>) fract).text = null;
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

  /**
   * @return the explicit {@link PlainFraction plain} {@link #toString() string representation} of this
   *         {@link Fraction}.
   */
  protected String getText() {

    if (this.text == null) {
      this.text = this.beats + "/" + this.unit;
    }
    return this.text;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public T makeImmutable() {

    this.immutable = true;
    return self();
  }

  @Override
  public int hashCode() {

    return 31 * this.beats + this.unit;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    AbstractFraction<?> other = (AbstractFraction<?>) obj;
    return isEqualTo(other);
  }

  /**
   * @param other the {@link AbstractFraction} to compare to.
   * @return {@code true} if considered {@link Object#equals(Object) equal}, {@code false} otherwise.
   */
  protected boolean isEqualTo(AbstractFraction<?> other) {

    if (other == null) {
      return false;
    } else if (this.beats != other.beats) {
      return false;
    } else if (this.unit != other.unit) {
      return false;
    } else if (!getText().equals(other.getText())) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(getText());
  }

}
