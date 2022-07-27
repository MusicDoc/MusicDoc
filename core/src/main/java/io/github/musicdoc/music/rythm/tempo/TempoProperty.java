package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.property.AbstractProperty;

/**
 * {@link io.github.musicdoc.property.Property} with {@link #getValue() value} of {@link #getType() type} {@link Tempo}.
 */
public class TempoProperty extends AbstractProperty<Tempo> {

  private Tempo value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public TempoProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public TempoProperty(String name, Tempo value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Tempo> getType() {

    return Tempo.class;
  }

  @Override
  public Tempo getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Tempo newValue) {

    this.value = newValue;
  }

  @Override
  protected Tempo parseValue(String valueAsString) {

    return TempoMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
