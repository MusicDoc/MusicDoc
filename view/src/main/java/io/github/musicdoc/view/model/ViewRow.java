package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A row that displays a fragment of the {@link io.github.musicdoc.stave.system.StaveSystem} as a single row from the
 * left to the right.
 */
public class ViewRow {

  private ViewBlock block;

  private final List<ViewColumn> columns;

  /**
   * The constructor.
   */
  public ViewRow() {

    super();
    this.columns = new ArrayList<>();
    this.block = new ViewBlock();
  }

  /**
   * @return columns
   */
  public List<ViewColumn> getColumns() {

    return this.columns;
  }

  /**
   * @param column the {@link ViewColumn} to add.
   */
  public void addColumn(ViewColumn column) {

    this.columns.add(column);
  }

  /**
   * @param cells the {@link ViewCell}s to add.
   */
  public void addColumn(ViewCell... cells) {

    ViewColumn column = new ViewColumn();
    for (ViewCell cell : cells) {
      column.addCell(cell);
    }
    addColumn(column);
  }

  /**
   * @return the {@link ViewBlock} to view static content independent from {@link #getColumns() columns} such as
   *         {@link io.github.musicdoc.score.section.ScoreSectionName section names} or {@link ViewItemStave staves}.
   */
  public ViewBlock getBlock() {

    return this.block;
  }

  /**
   * @param block new value of {@link #getBlock()}.
   */
  public void setBlock(ViewBlock block) {

    this.block = block;
  }

}
