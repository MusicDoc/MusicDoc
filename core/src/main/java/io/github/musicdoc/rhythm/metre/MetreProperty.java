package io.github.musicdoc.rhythm.metre;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * {@link SimpleProperty} with {@link #get() value} {@link #getValueClass() type} {@link Metre}.
 */
public final class MetreProperty extends SimpleProperty<Metre> {

  private Metre value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MetreProperty(String name, PropertyMetadata<Metre> metadata) {

    super(name, metadata);
  }

  @Override
  public Class<Metre> getValueClass() {

    return Metre.class;
  }

  @Override
  public Metre getFallbackSafeValue() {

    return Metre._4_4;
  }

  @Override
  public Metre doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Metre newValue) {

    this.value = newValue;
  }

  @Override
  public Metre parse(String valueAsString) {

    return MetreMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
