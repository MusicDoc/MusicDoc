package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.rhythm.fraction.FractionVariation;

/**
 * {@link FractionVariation} with {@link MutableObject}. Used internally to avoid generic type polution of
 * {@link FractionVariation}.
 *
 * @param <T> type if this object itself.
 */
public interface AbstractFractionVariation<T extends AbstractFractionVariation<T>>
    extends FractionVariation, MutableObject<T> {

  @Override
  AbstractFractionVariation<?> getVariation();

  /**
   * @param variation the {@link MusicalValueVariation} to add to the beginning.
   * @return a {@link ComposedVariation} with the given {@link MusicalValueVariation} followed by this variation.
   */
  default AbstractFractionVariation<?> prepend(MusicalValueVariation variation) {

    if (variation == MusicalValueVariation.NONE) {
      return this;
    }
    return new ComposedVariation(variation, this);
  }

  /**
   * @param variation the {@link MusicalValueVariation} to add to the end.
   * @return a {@link ComposedVariation} with the given {@link MusicalValueVariation} appended to the end.
   */
  AbstractFractionVariation<?> append(MusicalValueVariation variation);

}
