package io.github.musicdoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple implementation of {@link MutableObjecteCopier}.
 */
public final class MutableObjecteHelper {

  private MutableObjecteHelper() {

  }

  /**
   * @param <T> type of the {@link List} elements.
   * @param list the {@link List} to create an immutable copy of.
   * @return the immutable copy of the given {@link List}.
   */
  public static <T> List<T> makeImmutableFlat(List<T> list) {

    return Collections.unmodifiableList(new ArrayList<>(list));
  }

  /**
   * @param <T> type of the {@link List} elements.
   * @param list the {@link List} to create an immutable copy of.
   * @return the immutable copy of the given {@link List}.
   */
  public static <T extends MutableObject<T>> List<T> makeImmutableRecursive(List<T> list) {

    for (T mutable : list) {
      mutable.makeImmutable();
    }
    return Collections.unmodifiableList(new ArrayList<>(list));
  }

}
