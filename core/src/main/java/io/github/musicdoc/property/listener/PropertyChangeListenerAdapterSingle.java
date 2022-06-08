package io.github.musicdoc.property.listener;

import io.github.musicdoc.ObjectHelper;
import io.github.musicdoc.property.Property;

/**
 * Implementation of {@link PropertyChangeListenerAdapter} for a single registered {@link PropertyChangeListener}.
 *
 * @param <V> type of the property value.
 */
public class PropertyChangeListenerAdapterSingle<V> extends PropertyChangeListenerAdapter<V> {

  private final PropertyChangeListener<? super V> listener;

  private final Property<V> property;

  private V value;

  /**
   * The constructor.
   *
   * @param listener the single {@link PropertyChangeListener}.
   * @param property the {@link Property} to manage.
   */
  public PropertyChangeListenerAdapterSingle(PropertyChangeListener<? super V> listener, Property<V> property) {

    super();
    this.listener = listener;
    this.property = property;
    this.value = property.getValue();
  }

  @Override
  public PropertyChangeListenerAdapter<V> addListener(PropertyChangeListener<? super V> l, Property<V> p) {

    return new PropertyChangeListenerAdapterMultiple<>(this.listener, l, this.property);
  }

  @Override
  public PropertyChangeListenerAdapter<V> removeListener(PropertyChangeListener<? super V> l) {

    if (l.equals(this.listener)) {
      return PropertyChangeListenerAdapterEmpty.get();
    }
    return this;
  }

  @Override
  public void fireChange() {

    final V oldValue = this.value;
    this.value = this.property.getValue();
    if (!ObjectHelper.equals(this.value, oldValue)) {
      try {
        this.listener.onChange(this.property, oldValue, this.value);
      } catch (Exception e) {
        Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
      }
    }
  }
}
