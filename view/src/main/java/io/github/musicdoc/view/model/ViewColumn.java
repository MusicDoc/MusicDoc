package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link ViewColumn} represents a column as a vertical slice of a
 * {@link io.github.musicdoc.stave.system.StaveSystem}. It therefore contains the {@link ViewCell}s of the
 * {@link io.github.musicdoc.stave.system.StaveSystem} to be played at the same time from top to bottom.
 *
 */
public class ViewColumn implements ViewObject {

  private final List<ViewCell> cells;

  /**
   * The constructor.
   */
  public ViewColumn() {

    super();
    this.cells = new ArrayList<>();
  }

  /**
   * @return cells
   */
  public List<ViewCell> getCells() {

    return this.cells;
  }

  /**
   * @param cell the {@link ViewCell} to add.
   */
  public void addCell(ViewCell cell) {

    this.cells.add(cell);
  }

}
