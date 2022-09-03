package io.github.musicdoc.rhythm.fraction;

import java.util.Objects;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;

/**
 * Abstract base implementation of {@link Fraction}.
 *
 * @param <T> type of this class itself.
 */
public abstract class AbstractFraction<T extends AbstractFraction<T>> extends AbstractMusicalObject
    implements Fraction, MutableObject<T> {

  /** @see #isImmutable() */
  protected boolean immutable;

  /**
   * The constructor.
   */
  public AbstractFraction() {

    super();
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AbstractFraction(AbstractFraction<T> fraction, MutableObjecteCopier copier) {

    this();
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
  public T makeImmutable() {

    this.immutable = true;
    return self();
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
    } else if (getBeats() != other.getBeats()) {
      return false;
    } else if (getUnit() != other.getUnit()) {
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
      Fraction plain = getPlain();
      if (plain == this) {
        sb.append(getBeats());
        sb.append("/");
        sb.append(getUnit());
      } else {
        plain.toString(sb);
      }
    } else {
      sb.append(text);
    }
    FractionVariation variation = getVariation();
    if (variation != MusicalValueVariation.NONE) {
      variation.toString(sb);
    }
  }

}
