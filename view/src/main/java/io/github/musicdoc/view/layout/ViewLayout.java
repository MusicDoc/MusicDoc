package io.github.musicdoc.view.layout;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.view.model.ViewItem;

/**
 * State for layout alignment.
 */
public class ViewLayout {

  private final Map<ViewPlacement, ViewPlacementHeight> map;

  private ViewStaveLayout staveLayout;

  private double columnWidth;

  /**
   * The constructor.
   */
  public ViewLayout() {

    super();
    this.map = new HashMap<>();
    this.staveLayout = new ViewStaveLayout();
  }

  /**
   * @param placement the {@link ViewPlacement}.
   * @return the {@link ViewPlacementHeight} for the given {@link ViewPlacement} or {@code null} if none available.
   */
  public ViewPlacementHeight getHeight(ViewPlacement placement) {

    return this.map.get(placement);
  }

  /**
   * @param item the {@link ViewItem} to visit. Will adjust the layout accordingly so the {@link #getColumnWidth()
   *        column width} and {@link #getHeight(ViewPlacement) row height} gets updated.
   */
  public void visit(ViewItem item) {

    ViewPlacement placement = item.getPlacement();
    ViewPlacementHeight height = this.map.computeIfAbsent(placement, (p) -> new ViewPlacementHeight());
    height.visit(item.getY0(), item.getY1());
    double width = item.getWidth();
    if (width > this.columnWidth) {
      this.columnWidth = width;
    }
  }

  /**
   * @return the width of the current column.
   */
  public double getColumnWidth() {

    return this.columnWidth;
  }

  /**
   * @return the {@link ViewStaveLayout}.
   */
  public ViewStaveLayout getStaveLayout() {

    return this.staveLayout;
  }

  public void resetColumn() {

    this.columnWidth = 0;
  }

  public void resetRow() {

    resetColumn();
    this.map.clear();
    this.staveLayout = new ViewStaveLayout();
  }

}
