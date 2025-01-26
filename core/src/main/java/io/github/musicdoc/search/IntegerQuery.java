package io.github.musicdoc.search;

/**
 * {@link NonStringQuery} with {@link #getQuery() value} type {@link Integer}.
 */
public class IntegerQuery extends NonStringQuery<Integer> {

  /**
   * The constructor.
   *
   * @param value the {@link #getQuery() value to search}.
   * @param propertyName the {@link #getPropertyName() property name}.
   */
  public IntegerQuery(Integer value, String propertyName) {

    super(value, propertyName);
  }

  @Override
  protected Class<Integer> getValueClass() {

    return Integer.class;
  }

}
