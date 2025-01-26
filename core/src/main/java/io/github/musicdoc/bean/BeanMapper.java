package io.github.musicdoc.bean;

import io.github.mmm.bean.WritableBean;

/**
 * Abstract base class for a mapper to map a {@link WritableBean} (e.g. to XML).
 */
public abstract class BeanMapper {

  private final WritableBean bean;

  /**
   * The constructor.
   *
   * @param bean the {@link #getBean() bean to map}.
   */
  public BeanMapper(WritableBean bean) {

    super();
    this.bean = bean;
  }

  /**
   * @return the bean to map.
   */
  public WritableBean getBean() {

    return this.bean;
  }

}
