package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.rhythm.fraction.AdvancedFraction;

/**
 * Implementation of {@link AbstractFractionVariation} allowing to composes multiple {@link MusicalValueVariation}s.
 */
public class ComposedVariation extends AdvancedFraction<ComposedVariation>
    implements AbstractFractionVariation<ComposedVariation> {

  /**
   * The constructor.
   *
   * @param plain the {@link #getPlain() plain} {@link MusicalValueVariation}.
   * @param variation the next {@link #getVariation() variation}.
   */
  public ComposedVariation(MusicalValueVariation plain, AbstractFractionVariation<?> variation) {

    super(plain, variation);
  }

  private ComposedVariation(ComposedVariation fraction, MutableObjecteCopier copier) {

    super(fraction, copier);
  }

  @Override
  public ComposedVariation copy(MutableObjecteCopier copier) {

    return new ComposedVariation(this, copier);
  }

  @Override
  public MusicalValueVariation getPlain() {

    return (MusicalValueVariation) super.getPlain();
  }

  @Override
  public int getPunctuationCount() {

    int punctuation = getPlain().getPunctuationCount();
    if (punctuation == 0) {
      punctuation = this.variation.getPunctuationCount();
    }
    return punctuation;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    return ((MusicalValueVariation) this.plain).getGlyphs() + this.variation.getGlyphs();
  }

  @Override
  public AbstractFractionVariation<?> append(MusicalValueVariation lastVariation) {

    if (lastVariation == MusicalValueVariation.NONE) {
      return this;
    } else if (isImmutable()) {
      return copy().append(lastVariation);
    } else {
      AbstractFractionVariation<?> next = getVariation();
      AbstractFractionVariation<?> newNext = next.append(lastVariation);
      if (newNext != next) {
        setVariation(newNext);
      }
      return this;
    }
  }

}
