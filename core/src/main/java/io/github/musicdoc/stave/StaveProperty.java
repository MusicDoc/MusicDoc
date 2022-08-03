package io.github.musicdoc.stave;

import io.github.musicdoc.property.PropertyAccessor;

/**
 * Abstract base class for a {@link PropertyAccessor} of a {@link Stave} property.
 *
 * @param <T> type of the property value.
 */
abstract class StaveProperty<T> implements PropertyAccessor<AbstractStave<?>, T> {

  private final String name;

  public StaveProperty(String name) {

    this.name = name;
  }

  @Override
  public String toString() {

    return this.name;
  }
}
