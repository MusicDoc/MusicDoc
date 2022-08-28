package io.github.musicdoc.rythm.fraction;

import io.github.musicdoc.property.AbstractProperty;

/**
 * {@link io.github.musicdoc.property.Property} with {@link #getValue() value} {@link #getType() type}
 * {@link PlainFraction}.
 */
public class PlainFractionProperty extends AbstractProperty<PlainFraction> {

  private PlainFraction value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public PlainFractionProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public PlainFractionProperty(String name, PlainFraction value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<PlainFraction> getType() {

    return PlainFraction.class;
  }

  @Override
  public PlainFraction getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(PlainFraction newValue) {

    this.value = newValue;
  }

  @Override
  protected PlainFraction parseValue(String valueAsString) {

    return PlainFractionMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
