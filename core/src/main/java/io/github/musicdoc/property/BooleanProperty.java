package io.github.musicdoc.property;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link Boolean}.
 */
public class BooleanProperty extends AbstractProperty<Boolean> {

  private Boolean value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public BooleanProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public BooleanProperty(String name, Boolean value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Boolean> getType() {

    return Boolean.class;
  }

  @Override
  public Boolean getValue() {

    return this.value;
  }

  @Override
  public Boolean getValueOrDefault() {

    if (this.value == null) {
      return Boolean.FALSE;
    }
    return this.value;
  }

  /**
   * @return the primitive {@link #getValue() value}. Will be {@code false} if {@link #getValue() value} is
   *         {@code null}.
   */
  public boolean get() {

    if (this.value == null) {
      return false;
    } else {
      return this.value.booleanValue();
    }
  }

  /**
   * @param value the new {@link #get() value}.
   */
  public void set(boolean value) {

    setValue(Boolean.valueOf(value));
  }

  @Override
  protected void doSetValue(Boolean newValue) {

    this.value = newValue;
  }

  /**
   * Negates the {@link #get() value}.
   */
  public void negate() {

    set(!get());
  }

  @Override
  protected Boolean parseValue(String valueAsString) {

    return Boolean.parseBoolean(valueAsString);
  }

}
