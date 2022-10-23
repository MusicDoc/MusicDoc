package io.github.musicdoc.view.model;

import io.github.musicdoc.score.cell.ScoreCell;

/**
 * A {@link ViewCell} is the {@link ViewObject} of a {@link ScoreCell}. It composes multiple {@link ViewItem}s to render
 * the elements of the {@link ScoreCell}.
 */
public class ViewCell extends AbstractViewBlock {

  private final ScoreCell scoreCell;

  /**
   * The constructor.
   *
   * @param scoreCell the {@link #getScoreCell() score cell}.
   */
  public ViewCell(ScoreCell scoreCell) {

    super();
    this.scoreCell = scoreCell;
  }

  /**
   * @return the {@link ScoreCell} to visualize with this {@link ViewCell}.
   */
  public ScoreCell getScoreCell() {

    return this.scoreCell;
  }

}
