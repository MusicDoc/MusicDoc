package io.github.musicdoc;

/**
 * A simple fallback implementation of {@link MutableObjecteCopier} that always creates a new
 * {@link MutableObject#copy() copy}.
 *
 * @see MutableObjecteCopierWithCache
 */
public final class MutableObjecteCopierSimple implements MutableObjecteCopier {

  /** The singleton instance. */
  public static final MutableObjecteCopierSimple INSTANCE = new MutableObjecteCopierSimple();

  private MutableObjecteCopierSimple() {

    super();
  }

  @Override
  public <T extends MutableObject<T>> T copy(T object) {

    return object.copy(this);
  }

}
