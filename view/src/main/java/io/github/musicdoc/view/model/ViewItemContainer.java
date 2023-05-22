package io.github.musicdoc.view.model;

import java.util.Iterator;

/**
 * Interface for a group containing {@link ViewItem}s.
 */
public interface ViewItemContainer extends Iterable<ViewItem> {

  /**
   * @return the number of {@link #getItem(int) items} contained in this group.
   */
  int getItemCount();

  /**
   * @param i the index of the requested {@link ViewItem}. Should be in the range from {@code 0} to
   *        {@link #getItemCount() itemCount} - 1.
   * @return the {@link ViewItem} at the given index or {@code null} for invalid index.
   */
  ViewItem getItem(int i);

  @Override
  default Iterator<ViewItem> iterator() {

    return new ViewItemIterator(this);
  }

}
