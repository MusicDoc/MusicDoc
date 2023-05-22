package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for a container of {@link ViewItem}s such as a {@link ViewBlock} or a {@link ViewCell}.
 */
public abstract class AbstractViewBlock implements ViewObject, ViewItemContainer {

  private final List<ViewItemContainer> items;

  /**
   * The constructor.
   */
  public AbstractViewBlock() {

    super();
    this.items = new ArrayList<>();
  }

  @Override
  public int getItemCount() {

    int count = 0;
    for (ViewItemContainer item : this.items) {
      count += item.getItemCount();
    }
    return count;
  }

  @Override
  public ViewItem getItem(int i) {

    if (i < 0) {
      return null;
    }
    int pos = i;
    for (ViewItemContainer item : this.items) {
      int count = item.getItemCount();
      if (pos < count) {
        return item.getItem(pos);
      } else {
        pos = pos - count;
      }
    }
    return null;
  }

  @Override
  public Iterator<ViewItem> iterator() {

    return new ViewItemIterator(this.items.iterator());
  }

  /**
   * @param item the {@link ViewItemContainer} to add to the {@link #getItem(int) items}.
   */
  public void addItem(ViewItemContainer item) {

    this.items.add(item);
  }

}
