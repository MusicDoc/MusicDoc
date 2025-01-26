package io.github.musicdoc.rhythm.fraction;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * {@link SimpleProperty} with {@link #get() value} {@link #getValueClass() type} {@link PlainFraction}.
 */
public final class PlainFractionProperty extends SimpleProperty<PlainFraction> {

  private PlainFraction value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public PlainFractionProperty(String name, PropertyMetadata<PlainFraction> metadata) {

    super(name, metadata);
  }

  @Override
  public Class<PlainFraction> getValueClass() {

    return PlainFraction.class;
  }

  @Override
  public PlainFraction getFallbackSafeValue() {

    return PlainFraction._1_1;
  }

  @Override
  public PlainFraction doGet() {

    return this.value;
  }

  @Override
  protected void doSet(PlainFraction newValue) {

    this.value = newValue;
  }

  @Override
  public PlainFraction parse(String valueAsString) {

    return PlainFractionMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
