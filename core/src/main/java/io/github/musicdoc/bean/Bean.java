package io.github.musicdoc.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.musicdoc.property.BeanProperty;
import io.github.musicdoc.property.Property;

/**
 * Abstract base class for an advanced Java bean.
 */
public abstract class Bean {

  private static final Logger LOG = LoggerFactory.getLogger(Bean.class);

  private static final String TAG = "Bean";

  private final Map<String, Property<?>> propertyMapInternal;

  private final Map<String, Property<?>> propertyMap;

  protected Bean() {

    super();
    this.propertyMapInternal = new HashMap<>();
    this.propertyMap = Collections.unmodifiableMap(this.propertyMapInternal);
  }

  /**
   * @param copy the {@link Bean} to copy.
   */
  @SuppressWarnings("unchecked")
  protected void copy(Bean copy) {

    assert (copy.getClass() == getClass());
    for (Property<?> copyProperty : copy.getProperties()) {
      @SuppressWarnings("rawtypes")
      Property myProperty = this.propertyMap.get(copyProperty.getName());
      assert (copyProperty.getClass() == myProperty.getClass());
      myProperty.setValue(copyProperty.getValue());
    }
  }

  /**
   * @return the {@link Collections#unmodifiableMap(Map)} unmodifyable} {@link Map} with the properties of this
   *         {@link Bean}.
   */
  protected Map<String, Property<?>> getPropertyMap() {

    return this.propertyMap;
  }

  /**
   * @param <T>
   * @param <P>
   * @param property the {@link Property} to register.
   * @return
   */
  protected <T, P extends Property<T>> P register(P property) {

    Property<?> old = this.propertyMapInternal.put(property.getName(), property);
    if (old != null) {
      throw new IllegalStateException("Duplicate property for key: " + property.getName());
    }
    return property;
  }

  /**
   * @return the {@link Collection} with the {@link Property#getName() names} of the {@link Property properties}
   *         contained in this {@link Bean}.
   */
  public Collection<String> getPropertyNames() {

    return getPropertyMap().keySet();
  }

  /**
   * @return the {@link Collection} of {@link Property properties} contained in this {@link Bean}.
   */
  public Collection<Property<?>> getProperties() {

    return getPropertyMap().values();
  }

  public Collection<Property<?>> getProperties(boolean recursive, boolean includeParents) {

    List<Property<?>> properties = new ArrayList<>();
    collectProperties(properties, recursive, includeParents);
    return properties;
  }

  private void collectProperties(Collection<Property<?>> properties, boolean recursive, boolean includeParents) {

    for (Property<?> p : getPropertyMap().values()) {
      if (p instanceof BeanProperty) {
        if (recursive) {
          Bean config = ((BeanProperty) p).getValue();
          if (config != null) {
            config.collectProperties(properties, recursive, includeParents);
          }
        }
        if (includeParents) {
          properties.add(p);
        }
      } else {
        properties.add(p);
      }
    }
  }

  /**
   * @param name the {@link Property#getName() name} of the requested property.
   * @param recursive - {@code true} to search for properties recursively in child {@link BeanProperty} instances,
   *        {@code false} otherwise.
   * @return the requested {@link Property} or {@code null} if not found.
   */
  public Property<?> getProperty(String name, boolean recursive) {

    Property<?> property = getPropertyMap().get(name);
    if (property != null) {
      return property;
    }
    if (recursive) {
      for (Property<?> p : getPropertyMap().values()) {
        if (p instanceof BeanProperty) {
          BeanProperty<?> cp = (BeanProperty<?>) p;
          Bean config = cp.getValue();
          if (config != null) {
            property = config.getProperty(name, recursive);
            if (property != null) {
              return property;
            }
          }
        }
      }
    }
    return null;
  }

  public void setValue(String name, String value) {

    Property<?> property = getProperty(name, true);
    if (property != null) {
      property.setValueAsString(value);
    } else {
      LOG.warn(TAG, "Property '{}' is undefined and cannot be set.", name);
    }
  }

}
