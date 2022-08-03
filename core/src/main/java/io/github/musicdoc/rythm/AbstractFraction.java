package io.github.musicdoc.rythm;

import io.github.musicdoc.AbstractMusicalObject;

/**
 * Abstract base implementation of {@link Fraction}.
 */
public abstract class AbstractFraction extends AbstractMusicalObject implements Fraction {

  /** @see #getBeats() */
  protected int beats;

  /** @see #getFraction() */
  protected int fraction;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param fraction the {@link #getFraction() fraction}.
   */
  public AbstractFraction(int beats, int fraction) {

    super();
    if (beats < 0) {
      throw new IllegalArgumentException("Invalid beats value " + beats + " - must be positive!");
    }
    if (fraction < 1) {
      throw new IllegalArgumentException("Invalid fraction value " + fraction + " - must be positive!");
    }
    this.beats = beats;
    this.fraction = fraction;
  }

  @Override
  public int getBeats() {

    return this.beats;
  }

  @Override
  public int getFraction() {

    return this.fraction;
  }

  @Override
  public int hashCode() {

    return 31 * this.beats + this.fraction;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    AbstractFraction other = (AbstractFraction) obj;
    return isEqualTo(other);
  }

  /**
   * @param other the {@link AbstractFraction} to compare to.
   * @return {@code true} if considered {@link Object#equals(Object) equal}, {@code false} otherwise.
   */
  protected boolean isEqualTo(AbstractFraction other) {

    if (other == null) {
      return false;
    }
    if (this.beats != other.beats) {
      return false;
    }
    if (this.fraction != other.fraction) {
      return false;
    }
    return true;

  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.beats);
    sb.append('/');
    sb.append(this.fraction);
  }
}
