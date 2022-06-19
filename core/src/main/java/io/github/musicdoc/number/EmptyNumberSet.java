package io.github.musicdoc.number;

/**
 * The empty {@link NumberSet}.
 */
class EmptyNumberSet implements NumberSet {

  /** The singleton instanc. */
  static final EmptyNumberSet INSTANCE = new EmptyNumberSet();

  private EmptyNumberSet() {

    super();
  }

  @Override
  public int getMin() {

    return 0;
  }

  @Override
  public int getMax() {

    return 0;
  }

  @Override
  public boolean contains(int number) {

    return false;
  }

  @Override
  public String toString() {

    return "";
  }

}
