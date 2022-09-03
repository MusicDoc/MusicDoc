package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.rhythm.fraction.FractionVariation;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.value.MusicalValue;

/**
 * A {@link MusicalValueVariation} is an {@link #getVariation() atomic} {@link FractionVariation} that modifies the
 * {@link #getPlain() plain fraction} of a {@link MusicalValue}. There are particular symbols to visualize a
 * {@link MusicalValueVariation} in a score.
 */
public abstract class MusicalValueVariation extends SimpleFraction<MusicalValueVariation>
    implements AbstractFractionVariation<MusicalValueVariation> {

  /**
   * No variation.
   */
  public static final MusicalValueVariation NONE = new None();

  private final String text;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param text the {@link #toString() string representation}
   */
  protected MusicalValueVariation(int beats, int unit, String text) {

    super(beats, unit);
    this.text = text;
    makeImmutable();
  }

  @Override
  public MusicalValueVariation copy(MutableObjecteCopier copier) {

    throw new UnsupportedOperationException();
  }

  @Override
  public MusicalValueVariation getPlain() {

    return this;
  }

  @Override
  protected String getText() {

    return this.text;
  }

  @Override
  public AbstractFractionVariation<?> append(MusicalValueVariation variation) {

    if (variation == MusicalValueVariation.NONE) {
      return this;
    } else {
      return new ComposedVariation(this, variation);
    }
  }

  private static final class None extends MusicalValueVariation {

    private None() {

      super(1, 1, "");
      makeImmutable();
    }

    @Override
    public String getGlyphs(MusicalGlyphsContext context) {

      return "";
    }

    @Override
    public AbstractFractionVariation<?> prepend(MusicalValueVariation variation) {

      return variation;
    }

    @Override
    public AbstractFractionVariation<?> append(MusicalValueVariation variation) {

      return variation;
    }

  }
}
