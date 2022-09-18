package io.github.musicdoc.decoration;

import java.util.List;

/**
 * Interface for an item containing {@link MusicalDecoration}s.
 */
public interface DecoratedItem {

  /**
   * @param itemSuffix the {@link MusicalDecoration#isItemSuffix() item suffix} flag.
   * @return {@code true} if this item {@link #getDecoration(int) has a decoration} with the given
   *         {@link MusicalDecoration#isItemSuffix() item suffix} flag.
   */
  default boolean hasDecorationsWithSuffix(boolean itemSuffix) {

    int size = getDecorationCount();
    for (int i = 0; i < size; i++) {
      MusicalDecoration decoration = getDecoration(i);
      if (decoration.isItemSuffix() == itemSuffix) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the {@link List#size() count} of {@link #getDecoration(int) decorations}.
   */
  int getDecorationCount();

  /**
   * @param i the index of the requested {@link MusicalDecoration}.
   * @return the {@link MusicalDecoration} at the given index or {@code null} if the index is out of range.
   */
  MusicalDecoration getDecoration(int i);

}
