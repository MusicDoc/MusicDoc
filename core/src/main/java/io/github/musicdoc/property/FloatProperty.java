package io.github.musicdoc.property;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link Float}.
 */
public class FloatProperty extends AbstractProperty<Float> {

  private Float value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public FloatProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public FloatProperty(String name, Float value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Float> getType() {

    return Float.class;
  }

  @Override
  public Float getValue() {

    return this.value;
  }

  @Override
  public Float getValueOrDefault() {

    if (this.value == null) {
      return Float.valueOf(0);
    }
    return this.value;
  }

  /**
   * @return the primitive {@link #getValue() value} with {@code 0} instead of {@code null}.
   * @See {@link #getValue()}
   * @see #set(float)
   */
  public float get() {

    if (this.value == null) {
      return 0;
    } else {
      return this.value.floatValue();
    }
  }

  @Override
  protected void doSetValue(Float newValue) {

    this.value = newValue;
  }

  /**
   * @param value the primitive {@link #getValue() value}.
   * @see #get()
   * @see #setValue(Float)
   */
  public void set(float value) {

    setValue(Float.valueOf(value));
  }

  @Override
  protected Float parseValue(String valueAsString) {

    return Float.parseFloat(valueAsString);
  }

}
