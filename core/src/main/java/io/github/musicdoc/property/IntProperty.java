package io.github.musicdoc.property;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link Integer}.
 */
public class IntProperty extends AbstractProperty<Integer> {

  private Integer value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public IntProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public IntProperty(String name, Integer value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Integer> getType() {

    return Integer.class;
  }

  @Override
  public Integer getValue() {

    return this.value;
  }

  @Override
  public Integer getValueOrDefault() {

    if (this.value == null) {
      return Integer.valueOf(0);
    }
    return this.value;
  }

  public int get() {

    if (this.value == null) {
      return 0;
    } else {
      return this.value.intValue();
    }
  }

  @Override
  protected void doSetValue(Integer newValue) {

    this.value = newValue;
  }

  public void set(int value) {

    setValue(Integer.valueOf(value));
  }

  @Override
  protected Integer parseValue(String valueAsString) {

    return Integer.parseInt(valueAsString);
  }
}
