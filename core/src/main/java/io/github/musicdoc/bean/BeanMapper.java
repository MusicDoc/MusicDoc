package io.github.musicdoc.bean;

/**
 * Abstract base class for a mapper to map a {@link Bean} (e.g. to XML).
 */
public abstract class BeanMapper {

  private final Bean bean;

  /**
   * The constructor.
   *
   * @param bean the {@link #getBean() bean to map}.
   */
  public BeanMapper(Bean bean) {

    super();
    this.bean = bean;
  }

  /**
   * @return the bean to map.
   */
  public Bean getBean() {

    return this.bean;
  }

}
