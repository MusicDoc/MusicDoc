package io.github.musicdoc.rhythm.tempo;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;
import io.github.musicdoc.rhythm.fraction.PlainFraction;

/**
 * {@link SimpleProperty} with {@link #get() value} of {@link #getValueClass() type} {@link Tempo}.
 */
public final class TempoProperty extends SimpleProperty<Tempo> {

  private static final Tempo DEFAULT = new Tempo("", 120, "", PlainFraction._1_4);

  private Tempo value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public TempoProperty(String name, PropertyMetadata<Tempo> metadata) {

    super(name, metadata);
  }

  @Override
  public Class<Tempo> getValueClass() {

    return Tempo.class;
  }

  @Override
  public Tempo getFallbackSafeValue() {

    return DEFAULT;
  }

  @Override
  public Tempo doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Tempo newValue) {

    this.value = newValue;
  }

  @Override
  public Tempo parse(String valueAsString) {

    return TempoMapperMusicDoc.INSTANCE.read(valueAsString);
  }

}
