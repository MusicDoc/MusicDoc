package io.github.musicdoc.property;

/**
 * Interface to access a property from a bean.
 *
 * @param <B> type of the bean.
 * @param <T> type of the property value.
 */
public interface PropertyAccessor<B, T> {

  /**
   * @param bean the Java bean containing the requested property.
   * @return the value of the property. May be {@code null}.
   */
  T get(B bean);

  /**
   * @param bean the Java bean containing the property to set.
   * @param value the new value of the property. May be {@code null}.
   */
  void set(B bean, T value);

  /**
   * @return the name of the property.
   */
  @Override
  String toString();

}
