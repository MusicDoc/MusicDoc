package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for a container of {@link ViewItem}s such as a {@link ViewBlock} or a {@link ViewCell}.
 */
public abstract class AbstractViewBlock implements ViewObject {

  private final List<ViewItem> items;

  /**
   * The constructor.
   */
  public AbstractViewBlock() {

    super();
    this.items = new ArrayList<>();
  }

  /**
   * @return the {@link List} of {@link ViewItem}s contained in this block.
   */
  public List<ViewItem> getItems() {

    return this.items;
  }

  /**
   * @param item the {@link ViewItem} to add to the {@link #getItems() items}.
   */
  public void addItem(ViewItem item) {

    this.items.add(item);
  }

}
