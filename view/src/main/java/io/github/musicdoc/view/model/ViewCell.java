package io.github.musicdoc.view.model;

import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * A {@link ViewCell} is the {@link ViewObject} of a {@link ScoreCell}. It composes multiple {@link ViewItem}s to render
 * the elements of the {@link ScoreCell}.
 */
public class ViewCell extends AbstractViewBlock {

  private final ScoreCell scoreCell;

  private final StaveVoice voice;

  /**
   * The constructor.
   *
   * @param scoreCell the {@link #getScoreCell() score cell}.
   * @param voice the #get
   */
  public ViewCell(ScoreCell scoreCell, StaveVoice voice) {

    super();
    this.scoreCell = scoreCell;
    this.voice = voice;
  }

  /**
   * @return the {@link ScoreCell} to visualize with this {@link ViewCell}.
   */
  public ScoreCell getScoreCell() {

    return this.scoreCell;
  }

  /**
   * @return the {@link StaveVoice} the {@link #getScoreCell() score cell} and this {@link ViewCell} belongs to.
   */
  public StaveVoice getVoice() {

    return this.voice;
  }

}
