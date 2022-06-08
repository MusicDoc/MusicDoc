package io.github.musicdoc.property.listener;

import io.github.musicdoc.property.Property;

/**
 * Abstract base class for an adapter to manage {@link PropertyChangeListener}s.
 *
 * @param <V> type of the property value.
 */
public abstract class PropertyChangeListenerAdapter<V> {

  /**
   * @param listener the {@link PropertyChangeListener} to add.
   * @param property the owning {@link Property}.
   * @return the new {@link PropertyChangeListenerAdapter} or this instance itself.
   * @see Property#addListener(PropertyChangeListener)
   */
  public abstract PropertyChangeListenerAdapter<V> addListener(PropertyChangeListener<? super V> listener,
      Property<V> property);

  /**
   * @param listener the {@link PropertyChangeListener} to remove.
   * @return the new {@link PropertyChangeListenerAdapter} or this instance itself.
   * @see Property#removeListener(PropertyChangeListener)
   */
  public abstract PropertyChangeListenerAdapter<V> removeListener(PropertyChangeListener<? super V> listener);

  /**
   * Fires a {@link PropertyChangeListener#onChange(Property, Object, Object) change} event to all
   * {@link #addListener(PropertyChangeListener, Property) registered} {@link PropertyChangeListener listeners}.
   */
  public abstract void fireChange();
}
