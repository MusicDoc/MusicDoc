package io.github.musicdoc.property;

import io.github.musicdoc.property.listener.PropertyChangeListener;
import io.github.musicdoc.property.listener.PropertyChangeListenerAdapter;
import io.github.musicdoc.property.listener.PropertyChangeListenerAdapterEmpty;

/**
 * Abstract base implementation of {@link Property}.
 *
 * @param <V> type of the contained value.
 */
public abstract class AbstractProperty<V> implements Property<V> {

  private final String name;

  private PropertyChangeListenerAdapter<V> listenerAdapter;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public AbstractProperty(String name) {

    super();
    this.name = name;
    this.listenerAdapter = PropertyChangeListenerAdapterEmpty.get();
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void addListener(PropertyChangeListener<? super V> listener) {

    this.listenerAdapter = this.listenerAdapter.addListener(listener, this);
  }

  @Override
  public void removeListener(PropertyChangeListener<? super V> listener) {

    this.listenerAdapter = this.listenerAdapter.removeListener(listener);
  }

  @Override
  public boolean isReadOnly() {

    return false;
  }

  /**
   * @throws IllegalStateException if {@link #isReadOnly() read-only}.
   */
  protected final void requireWritable() {

    if (isReadOnly()) {
      throw new IllegalStateException("Bean (" + toString() + ") is readonly.");
    }
  }

  @Override
  public V getValueOrDefault() {

    return getValue();
  }

  @Override
  public final void setValue(V value) {

    requireWritable();
    doSetValue(value);
    fireChange();
  }

  /**
   * Called from {@link #setValue(Object)}.
   *
   * @param newValue the new {@link #getValue() value} to set.
   */
  protected abstract void doSetValue(V newValue);

  @Override
  public final void setValueAsString(String string) {

    V value = parseValue(string);
    setValue(value);
  }

  /**
   * @param valueAsString the property value as {@link String}.
   * @return the parsed property value.
   */
  protected abstract V parseValue(String valueAsString);

  /**
   * Has to be called whenever the {@link #getValue() value} {@link #setValue(Object) changes}.
   *
   * @see PropertyChangeListenerAdapter#fireChange()
   */
  protected void fireChange() {

    this.listenerAdapter.fireChange();
  }

  @Override
  public String toString() {

    return getName() + ":" + getValue();
  }
}
