package io.github.musicdoc.search;

import java.util.Objects;

/**
 * {@link AtomicQuery} for a single value.
 *
 * @param <V> the {@link #getValueClass() value class}.
 */
public abstract class ValueQuery<V> extends AtomicQuery<V> {

  /** @see #getQuery() */
  protected final V query;

  /**
   * The constructor.
   *
   * @param value the {@link #getQuery() value to search}.
   * @param propertyName the {@link #getPropertyName() property name}.
   */
  public ValueQuery(V value, String propertyName) {

    super(propertyName);
    Objects.requireNonNull(value);
    this.query = value;
  }

  /**
   * @return the value to search.
   */
  public V getQuery() {

    return this.query;
  }

}
