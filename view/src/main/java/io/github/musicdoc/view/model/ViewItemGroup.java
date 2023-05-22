package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link ViewItemContainer} that directly contains {@link ViewItem}s.
 */
public class ViewItemGroup implements ViewItemContainer {

  private final List<ViewItem> items;

  /**
   * The constructor.
   */
  public ViewItemGroup() {

    super();
    this.items = new ArrayList<>();
  }

  @Override
  public int getItemCount() {

    return this.items.size();
  }

  @Override
  public ViewItem getItem(int i) {

    if ((i < 0) || (i >= this.items.size())) {
      return null;
    }
    return this.items.get(i);
  }

  /**
   * @param item the {@link ViewItem} to add to this group.
   */
  public void addItem(ViewItem item) {

    Objects.requireNonNull(item);
    this.items.add(item);
  }

}
