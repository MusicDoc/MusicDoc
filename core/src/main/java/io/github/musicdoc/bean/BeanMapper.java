package io.github.musicdoc.bean;

public abstract class BeanMapper {

  private final Bean bean;

  public BeanMapper(Bean bean) {

    super();
    this.bean = bean;
  }

  public Bean getBean() {

    return this.bean;
  }

}
