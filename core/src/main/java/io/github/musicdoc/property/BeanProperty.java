package io.github.musicdoc.property;

import io.github.musicdoc.bean.Bean;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link Bean}.
 *
 * @param <V> type of the {@link Bean} value.
 */
public class BeanProperty<V extends Bean> extends AbstractProperty<V> {

  private final Class<V> type;

  private final V value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public BeanProperty(String name, V value) {

    this(name, (Class) value.getClass(), value);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param type the {@link #getType() property type}.
   * @param value the {@link #getValue() property value}.
   */
  public BeanProperty(String name, Class<V> type, V value) {

    super(name);
    this.value = value;
    this.type = type;
  }

  @Override
  public Class<V> getType() {

    return this.type;
  }

  @Override
  public V getValue() {

    return this.value;
  }

  @Override
  public boolean isReadOnly() {

    return true;
  }

  @Override
  protected void doSetValue(V newValue) {

    throw new IllegalStateException();
  }

  @Override
  protected V parseValue(String string) {

    throw new IllegalStateException();
  }
}
