package io.github.musicdoc;

import java.util.HashMap;
import java.util.Map;

/**
 * A stateful implementation of {@link MutableObjecteCopier} that uses an internal cache so that the same instance of a
 * {@link MutableObject} to {@link MutableObject#copy() copy} will result in the same {@link MutableObject#copy() copy}
 * instance. This is needed to copy an entire object-graph that may have different references to the same instance.
 * Without this cache in the {@link MutableObject#copy() copy} all these references would result in different instances
 * what is not correct. Instead you want to have all these references pointing to the same object also in the
 * {@link MutableObject#copy() copy}.
 */
public class MutableObjecteCopierWithCache implements MutableObjecteCopier {

  @SuppressWarnings("rawtypes")
  private final Map<MutableObject, MutableObject> copyMap;

  /**
   * The constructor.
   */
  public MutableObjecteCopierWithCache() {

    super();
    this.copyMap = new HashMap<>();
  }

  @Override
  public <T extends MutableObject<T>> T copy(T object) {

    T copy = (T) this.copyMap.computeIfAbsent(object, o -> o.copy());
    return copy;
  }

}
