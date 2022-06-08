package io.github.musicdoc.property;

import io.github.musicdoc.music.harmony.MusicalKey;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link MusicalKey}.
 */
public class MusicalKeyProperty extends AbstractProperty<MusicalKey> {

  private MusicalKey value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public MusicalKeyProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public MusicalKeyProperty(String name, MusicalKey value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<MusicalKey> getType() {

    return MusicalKey.class;
  }

  @Override
  public MusicalKey getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(MusicalKey newValue) {

    this.value = newValue;
  }

  @Override
  protected MusicalKey parseValue(String value) {

    return MusicalKey.fromName(value);
  }
}
