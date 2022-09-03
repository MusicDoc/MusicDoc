package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.property.AbstractProperty;

/**
 * {@link io.github.musicdoc.property.Property} with {@link #getValue() value} {@link #getType() type} {@link Metre}.
 */
public class MetreProperty extends AbstractProperty<Metre> {

  private Metre value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public MetreProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public MetreProperty(String name, Metre value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Metre> getType() {

    return Metre.class;
  }

  @Override
  public Metre getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Metre newValue) {

    this.value = newValue;
  }

  @Override
  protected Metre parseValue(String valueAsString) {

    return MetreMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
