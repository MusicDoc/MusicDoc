package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.data.ViewAttributeWriteWidth;

/**
 * Horizontal layout to compute {@link #getWidth() width} of multiple columns.
 */
public class ViewWidthLayout implements ViewAttributeWriteWidth {

  private double width;

  private ViewWidthLayout next;

  @Override
  public double getWidth() {

    return this.width;
  }

  @Override
  public void setWidth(double width) {

    this.width = width;
  }

  /**
   * @return the total width as sum this {@link #getWidth() width} values with all {@link #getNext() successors}.
   */
  public double getTotalWidth() {

    return getTotalWidth(0);
  }

  /**
   * @param space the width to add as space between each column.
   * @return the total width as sum this {@link #getWidth() width} values with all {@link #getNext() successors}
   *         including the given {@code space}.
   */
  public double getTotalWidth(double space) {

    double total = this.width;
    ViewWidthLayout current = this.next;
    while (current != null) {
      total = total + space + current.width;
      current = current.next;
    }
    return total;
  }

  /**
   * @return the next {@link ViewWidthLayout} or {@code null} for none.
   */
  public ViewWidthLayout getNext() {

    return this.next;
  }

  /**
   * @return the next {@link ViewWidthLayout}. Will be created lazily on first call.
   */
  public ViewWidthLayout getOrCreateNext() {

    if (this.next == null) {
      this.next = new ViewWidthLayout();
    }
    return this.next;
  }
}
