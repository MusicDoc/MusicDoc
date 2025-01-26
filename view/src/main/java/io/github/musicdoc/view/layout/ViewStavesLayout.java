package io.github.musicdoc.view.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.view.model.ViewItem;

/**
 * State for layout alignment of the {@link Stave}s of a {@link io.github.musicdoc.view.model.ViewRow}.
 */
public class ViewStavesLayout {

  private final Map<Stave, ViewStaveLayout> staveMap;

  private double columnWidth;

  /**
   * The constructor.
   */
  public ViewStavesLayout() {

    super();
    this.staveMap = new HashMap<>();
  }

  /**
   * @param stave the {@link Stave}.
   * @return the {@link ViewStaveLayout} for the given {@link Stave} or {@code null} if not defined.
   */
  public ViewStaveLayout getStaveLayout(Stave stave) {

    return this.staveMap.get(stave);
  }

  /**
   * @param stave the {@link Stave}.
   * @return the {@link ViewStaveLayout} for the given {@link Stave}. Will be created and added if not yet defined.
   */
  public ViewStaveLayout getOrCreateStaveLayout(Stave stave) {

    return this.staveMap.computeIfAbsent(stave, s -> new ViewStaveLayout());
  }

  /**
   * @param placement the {@link ViewPlacement}.
   * @return the {@link ViewPlacementHeight} for the given {@link ViewPlacement} or {@code null} if none available.
   */
  public ViewPlacementHeight getHeight(ViewPlacement placement) {

    ViewStaveLayout staveLayout = getStaveLayout(placement.getStave());
    if (staveLayout != null) {
      return staveLayout.getHeight(placement.getType());
    }
    return null;
  }

  /**
   * @param item the {@link ViewItem} to visit. Will adjust the layout accordingly so the {@link #getColumnWidth()
   *        column width} and {@link #getHeight(ViewPlacement) row height} gets updated.
   */
  public void visit(ViewItem item) {

    Stave stave = item.getStave();
    assert stave != null;
    ViewStaveLayout staveLayout = getOrCreateStaveLayout(stave);
    ViewPlacementType placementType = item.getPlacementType();
    ViewPlacementHeight height = staveLayout.getOrCreateHeight(placementType);
    height.visit(item.getY0(), item.getY1());
    if (placementType != ViewPlacementType.VOLTA) {
      double width = item.getWidth();
      if (width > this.columnWidth) {
        this.columnWidth = width;
      }
    }
  }

  /**
   * @param yStart the y-coordinate where to start the {@link io.github.musicdoc.view.model.ViewRow} with the
   *        {@link io.github.musicdoc.stave.system.StaveSystem}.
   * @param inStaveYSpace the vertical space to add between horizontal rows within the {@link Stave} (chord symbol,
   *        notes, lyrics).
   * @param betweenStaveYSpace the vertical space to add between {@link Stave}s.
   */
  public void layout(double yStart, double inStaveYSpace, double betweenStaveYSpace) {

    double y = yStart;
    List<Stave> orderedStaves = new ArrayList<>(this.staveMap.keySet());
    Collections.sort(orderedStaves);
    for (Stave stave : orderedStaves) {
      ViewStaveLayout staveLayout = this.staveMap.get(stave);
      y = staveLayout.layout(y, inStaveYSpace);
      double height = staveLayout.getTotalHeight(inStaveYSpace);
      y = y + height + betweenStaveYSpace;
    }
  }

  /**
   * @return the width of the current column.
   */
  public double getColumnWidth() {

    return this.columnWidth;
  }

  /** Resets the layout of the current column. */
  public void resetColumn() {

    this.columnWidth = 0;
  }

  /** Resets the layout of the current column and row. */
  public void resetRow() {

    resetColumn();
    this.staveMap.clear();
  }

}
