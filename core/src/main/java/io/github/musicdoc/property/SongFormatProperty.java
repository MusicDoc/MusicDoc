package io.github.musicdoc.property;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link String}.
 */
public class SongFormatProperty extends AbstractProperty<SongFormat> {

  private SongFormat value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public SongFormatProperty(String name) {

    this(name, SongFormatMusicDoc.INSTANCE);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public SongFormatProperty(String name, SongFormat value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<SongFormat> getType() {

    return SongFormat.class;
  }

  @Override
  public SongFormat getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(SongFormat newValue) {

    this.value = newValue;
  }

  @Override
  protected SongFormat parseValue(String valueAsString) {

    return SongFormat.get(valueAsString);
  }
}
