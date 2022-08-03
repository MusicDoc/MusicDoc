package io.github.musicdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Interface for an object to {@link MutableObject#copy() copy} instances of {@link MutableObject}.
 *
 * @see MutableObjecteCopierWithCache
 * @see MutableObjecteCopierSimple
 */
public interface MutableObjecteCopier {

  /**
   * @param <T> type of the {@link MutableObject}.
   * @param object the {@link MutableObject} to {@link MutableObject#copy() copy}.
   * @return a {@link MutableObject#copy() copy} of the given {@link MutableObject} that is cached so that subsequent
   *         calls will return the same {@link MutableObject#copy() copy} instance.
   */
  <T extends MutableObject<T>> T copy(T object);

  /**
   * @param <T> type of the {@link List} elements.
   * @param list the {@link List} to create an mutable copy of.
   * @return the immutable copy of the given {@link List}.
   */
  default <T extends MutableObject<T>> List<T> copyList(List<T> list) {

    return copyList(list, null);
  }

  /**
   * @param <T> type of the {@link List} elements.
   * @param list the {@link List} to create an mutable copy of.
   * @param postProcessor the optional {@link Consumer} that will be called on each element's
   *        {@link MutableObject#copy() copy}.
   * @return the immutable copy of the given {@link List}.
   */
  default <T extends MutableObject<T>> List<T> copyList(List<T> list, Consumer<T> postProcessor) {

    List<T> mutableList = new ArrayList<>(list.size());
    for (T mutable : list) {
      T copy = copy(mutable);
      if (postProcessor != null) {
        postProcessor.accept(copy);
      }
      mutableList.add(copy);
    }
    return mutableList;
  }

  /**
   * @param <T> type of the {@link List} elements.
   * @param list the {@link List} to create an immutable copy of.
   * @return the immutable copy of the given {@link List}.
   */
  default <T> List<T> copyListFlat(List<T> list) {

    return new ArrayList<>(list);
  }
}
