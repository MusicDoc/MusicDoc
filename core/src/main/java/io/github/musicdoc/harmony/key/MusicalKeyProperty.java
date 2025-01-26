package io.github.musicdoc.harmony.key;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * {@link SimpleProperty} with {@link #get() value} of {@link #getValueClass() type} {@link MusicalKey}.
 */
public final class MusicalKeyProperty extends SimpleProperty<MusicalKey> {

  private MusicalKey value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the (initial) {@link #get() value}.
   */
  public MusicalKeyProperty(String name, MusicalKey value) {

    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MusicalKeyProperty(String name, PropertyMetadata<MusicalKey> metadata) {

    super(name, metadata);
  }

  @Override
  public Class<MusicalKey> getValueClass() {

    return MusicalKey.class;
  }

  @Override
  public MusicalKey doGet() {

    return this.value;
  }

  @Override
  protected void doSet(MusicalKey newValue) {

    this.value = newValue;
  }

  @Override
  public MusicalKey parse(String valueAsString) {

    return MusicalKey.fromName(valueAsString);
  }

  @Override
  public MusicalKey getFallbackSafeValue() {

    return MusicalKey.C_MAJOR;
  }
}
