package io.github.musicdoc.property;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link String}.
 */
public class StringProperty extends AbstractProperty<String> {

  private String value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public StringProperty(String name) {

    this(name, "");
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public StringProperty(String name, String value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<String> getType() {

    return String.class;
  }

  @Override
  public String getValue() {

    return this.value;
  }

  @Override
  public String getValueOrDefault() {

    if (this.value == null) {
      return "";
    }
    return this.value;
  }

  @Override
  protected void doSetValue(String newValue) {

    this.value = newValue;
  }

  @Override
  protected String parseValue(String valueAsString) {

    return valueAsString;
  }
}
