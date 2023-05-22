package io.github.musicdoc.view.model;

import java.util.Iterator;

/**
 * {@link Iterator} of {@link ViewItem}s from {@link ViewItemContainer}.
 */
class ViewItemIterator implements Iterator<ViewItem> {

  private final Iterator<ViewItemContainer> iterator;

  private ViewItemContainer container;

  private int index;

  /**
   * The constructor.
   *
   * @param container the single {@link ViewItemContainer} to iterate.
   */
  public ViewItemIterator(ViewItemContainer container) {

    super();
    this.iterator = null;
    this.container = container;
  }

  /**
   * The constructor.
   *
   * @param iterator the {@link Iterator} of {@link ViewItemContainer}s to iterate.
   */
  public ViewItemIterator(Iterator<ViewItemContainer> iterator) {

    super();
    this.iterator = iterator;
    findNext();
  }

  private void findNext() {

    if (this.iterator == null) {
      return;
    }
    this.index = 0;
    while (this.iterator.hasNext()) {
      this.container = this.iterator.next();
      if ((this.container != null) && (this.container.getItemCount() > 0)) {
        return;
      }
    }
    this.container = null;
  }

  @Override
  public boolean hasNext() {

    return (this.container != null) && (this.index < this.container.getItemCount());
  }

  @Override
  public ViewItem next() {

    ViewItem item = this.container.getItem(this.index++);
    if (this.index >= this.container.getItemCount()) {
      findNext();
    }
    return item;
  }

}
