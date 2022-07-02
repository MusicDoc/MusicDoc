package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.property.AbstractProperty;

/**
 * TODO joerg This type ...
 *
 */
public class BeatProperty extends AbstractProperty<Beat> {

  private Beat value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public BeatProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public BeatProperty(String name, Beat value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Beat> getType() {

    return Beat.class;
  }

  @Override
  public Beat getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Beat newValue) {

    this.value = newValue;
  }

  @Override
  protected Beat parseValue(String valueAsString) {

    return BeatMapperMusicDoc.INSTANCE.parse(valueAsString);
  }
}
