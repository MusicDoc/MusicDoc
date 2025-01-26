package io.github.musicdoc.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.mmm.bean.Bean;
import io.github.mmm.bean.property.BeanProperty;
import io.github.mmm.property.WritableProperty;

/**
 * A {@link Bean} for configuration values.
 */
public abstract class Config extends Bean {

  /**
   * @param name the {@link WritableProperty#getName() name} of the requested property.
   * @param recursive - {@code true} to search for properties recursively in child {@link BeanProperty} instances,
   *        {@code false} otherwise.
   * @return the requested {@link WritableProperty} or {@code null} if not found.
   */
  public WritableProperty<?> getProperty(String name, boolean recursive) {

    WritableProperty<?> property = getProperty(name);
    if (property != null) {
      return property;
    }
    if (recursive) {
      for (WritableProperty<?> p : getProperties()) {
        if (p instanceof BeanProperty) {
          BeanProperty<?> cp = (BeanProperty<?>) p;
          Config config = (Config) cp.get();
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

  /**
   * @param recursive - {@code true} to collect properties recursively from nested {@link BeanProperty bean properties},
   *        {@code false} otherwise.
   * @param includeParents - {@code true} to include {@link BeanProperty bean properties}, {@code false} otherwise.
   * @return the {@link Collection} of the collected {@link WritableProperty properties}.
   */
  public Collection<WritableProperty<?>> getProperties(boolean recursive, boolean includeParents) {

    List<WritableProperty<?>> properties = new ArrayList<>();
    collectProperties(properties, recursive, includeParents);
    return properties;
  }

  private void collectProperties(Collection<WritableProperty<?>> properties, boolean recursive,
      boolean includeParents) {

    for (WritableProperty<?> p : getProperties()) {
      if (p instanceof BeanProperty) {
        if (includeParents) {
          properties.add(p);
        }
        if (recursive) {
          Config config = (Config) ((BeanProperty<?>) p).get();
          if (config != null) {
            config.collectProperties(properties, recursive, includeParents);
          }
        }
      } else {
        properties.add(p);
      }
    }
  }

}
