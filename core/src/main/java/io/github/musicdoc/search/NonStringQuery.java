package io.github.musicdoc.search;

import java.util.Objects;

import io.github.mmm.property.WritableProperty;

/**
 * {@link ValueQuery} for any {@link #getValueClass() value type} other than {@link String}.
 *
 * @param <V> the {@link #getValueClass() value class}.
 */
public abstract class NonStringQuery<V> extends ValueQuery<V> {

  /**
   * The constructor.
   *
   * @param value the {@link #getQuery() value to search}.
   * @param propertyName the {@link #getPropertyName() property name}.
   */
  public NonStringQuery(V value, String propertyName) {

    super(value, propertyName);
    assert (propertyName != null);
  }

  @Override
  protected double matchNormalizedValue(String normalizedValue) {

    return 0;
  }

  @Override
  protected double matchProperty(WritableProperty<V> property) {

    V propertyValue = property.get();
    if (Objects.equals(this.query, propertyValue)) {
      return 1;
    }
    return 0;
  }

}
