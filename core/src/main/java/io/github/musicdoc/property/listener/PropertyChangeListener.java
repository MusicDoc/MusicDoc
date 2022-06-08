package io.github.musicdoc.property.listener;

import io.github.musicdoc.property.Property;

/**
 * Interface for an even listener that gets notified {@link #onChange(Property, Object, Object) on changes} of a
 * {@link Property}.
 *
 * @param <V> type of the property value.
 */
public interface PropertyChangeListener<V> {

  /**
   * If the {@link Property#getValue() value} of a {@link Property} changed, this method is called for all
   * {@link Property#addListener(PropertyChangeListener) registered} {@link PropertyChangeListener}s.
   * <p>
   * In general is is considered bad practice to modify the observed value in this method.
   *
   * @param property the {@link Property} that changed its {@link Property#getValue() value}.
   * @param oldValue the old value.
   * @param newValue the new value.
   */
  void onChange(Property<? extends V> property, V oldValue, V newValue);
}
