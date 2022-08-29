package io.github.musicdoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base implementation of {@link MusicalObject}.
 */
public abstract class AbstractMusicalObject implements MusicalObject {

  /**
   * @param <T> type of the {@link List} elements.
   * @param item the single {@link List} element.
   * @return a mutable {@link List} containing the given element {@code item}.
   */
  protected static <T> List<T> asList(T item) {

    List<T> list = new ArrayList<>(2);
    list.add(item);
    return list;
  }

  /**
   * @param <T> type of the {@link List} elements.
   * @param array the array of elements.
   * @return a mutable {@link List} containing the elements from the given {@code array}.
   */
  protected static <T> List<T> asList(T[] array) {

    List<T> list = new ArrayList<>(array.length);
    for (T item : array) {
      list.add(item);
    }
    return list;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    toString(sb);
    return sb.toString();
  }

}
