package io.github.musicdoc.rhythm.fraction;

import java.util.Objects;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.rhythm.value.variation.AbstractFractionVariation;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariation;

/**
 * Extends {@link AbstractFraction} with {@link #getVariation() variation}.
 *
 * @param <T> type of this class itself.
 */
public abstract class AdvancedFraction<T extends AdvancedFraction<T>> extends AbstractFraction<T> {

  /** @see #getPlain() */
  protected final SimpleFraction<?> plain;

  /** @see #getVariation() */
  protected AbstractFractionVariation<?> variation;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param variation the {@link #getVariation() variation}
   */
  public AdvancedFraction(int beats, int unit, AbstractFractionVariation<?> variation) {

    super();
    this.plain = new PlainFraction(beats, unit);
    if (variation == null) {
      this.variation = MusicalValueVariation.NONE;
    } else {
      this.variation = variation;
    }
  }

  /**
   * The constructor.
   *
   * @param plain the {@link #getPlain() plain fraction}.
   * @param variation the {@link #getVariation() variation}
   */
  protected AdvancedFraction(SimpleFraction<?> plain, AbstractFractionVariation<?> variation) {

    super();
    Objects.requireNonNull(plain);
    this.plain = plain;
    if (variation == null) {
      this.variation = MusicalValueVariation.NONE;
    } else {
      this.variation = variation;
    }
  }

  /**
   * The copy constructor.
   *
   * @param fraction the {@link Fraction} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AdvancedFraction(AdvancedFraction<T> fraction, MutableObjecteCopier copier) {

    super(fraction, copier);
    // by intention do not use copier.copy here
    this.plain = copy(fraction.plain);
    this.variation = copy(fraction.variation);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Fraction & MutableObject<?>> T copy(T fraction) {

    if (fraction instanceof MusicalValueVariation) {
      return fraction;
    }
    return (T) fraction.copy();
  }

  @Override
  public T makeImmutable() {

    if (!this.immutable) {
      this.plain.makeImmutable();
      this.variation.makeImmutable();
      super.makeImmutable();
    }
    return self();
  }

  @Override
  public AbstractFractionVariation<?> getVariation() {

    return this.variation;
  }

  /**
   * @param variation the new value of {@link #getVariation() variation}.
   * @return a {@link MusicalValue} with the given {@link #getVariation() variation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public T setVariation(AbstractFractionVariation<?> variation) {

    if (variation == null) {
      variation = MusicalValueVariation.NONE;
    }
    if (this.variation == variation) {
      return self();
    }
    T value = makeMutable();
    // case is workaround for (Eclipse) compiler bug
    ((AdvancedFraction<?>) value).variation = variation;
    return value;
  }

  @Override
  public int getBeats() {

    return this.plain.getBeats() * this.variation.getBeats();
  }

  @Override
  public int getUnit() {

    return this.plain.getUnit() * this.variation.getUnit();
  }

  @Override
  public SimpleFraction<?> getPlain() {

    return this.plain;
  }

  @Override
  public void toString(StringBuilder sb) {

    this.plain.toString(sb);
    this.variation.toString(sb);
  }

}
