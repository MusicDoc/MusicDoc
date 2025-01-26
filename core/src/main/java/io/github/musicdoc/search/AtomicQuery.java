package io.github.musicdoc.search;

import io.github.mmm.property.WritableProperty;
import io.github.musicdoc.entity.EntityWithTitle;

/**
 * An atomic {@link SearchQuery} checking a single predicate.
 *
 * @param <V> the {@link #getValueClass() value class}.
 *
 * @see ConjunctionQuery
 */
public abstract class AtomicQuery<V> extends SearchQuery {

  /** @see #getPropertyName() */
  protected final String propertyName;

  /**
   * The constructor.
   *
   * @param propertyName the {@link #getPropertyName() property name}.
   */
  public AtomicQuery(String propertyName) {

    super();
    this.propertyName = propertyName;
  }

  /**
   * @return the {@link WritableProperty#getName() name of the property} from the {@link EntityWithTitle entity} this
   *         query is limited to or {@code null} for the default search that searches for matches in all searchable
   *         properties.
   */
  public String getPropertyName() {

    return this.propertyName;
  }

  /**
   * @return the {@link Class} reflecting the query value (e.g. {@link String}).
   * @see ValueQuery#getQuery()
   */
  protected abstract Class<V> getValueClass();

  /**
   * @param normalizedValue the normalized {@link String} value to match.
   * @return the resulting {@link SearchScore#getScore() search score}.
   */
  protected abstract double matchNormalizedValue(String normalizedValue);

  @Override
  protected double doMatch(EntityWithTitle entity) {

    if (this.propertyName == null) {
      return matchAnyProperty(entity);
    } else {
      WritableProperty<?> property = entity.getProperty(this.propertyName);
      if (property == null) {
        return 0;
      }
      if (property instanceof SearchableStringProperty) {
        SearchableStringProperty searchable = (SearchableStringProperty) property;
        return matchNormalizedValue(searchable.getNormalized());
      }
      if (getValueClass() != property.getValueClass()) {
        return 0;
      }
      @SuppressWarnings("unchecked")
      double score = matchProperty((WritableProperty<V>) property);
      return score;
    }
  }

  /**
   * @param property the {@link WritableProperty} to match.
   * @return the resulting {@link SearchScore#getScore() search score}.
   */
  protected abstract double matchProperty(WritableProperty<V> property);

  /**
   * @param entity the {@link EntityWithTitle entity} to match.
   * @return the resulting {@link SearchScore#getScore() search score}.
   */
  protected double matchAnyProperty(EntityWithTitle entity) {

    double result = 0;
    for (WritableProperty<?> property : entity.getProperties()) {
      if (getValueClass() == property.getValueClass()) {
        @SuppressWarnings("unchecked")
        double score = matchProperty((WritableProperty<V>) property);
        if (score == 1) {
          return 1;
        } else if (score > result) {
          result = score;
        }
      }
    }
    return result;
  }

}
