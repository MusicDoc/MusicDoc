package io.github.musicdoc.music.score.comment;

import java.util.Collections;

import io.github.musicdoc.music.score.ScoreCell;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreLine} for a general purpose {@link #getComment() comment}.
 */
public class ScoreCommentLine extends ScoreLine<ScoreCell<?>, ScoreCommentLine> {

  private final String comment;

  /**
   * The constructor.
   *
   * @param comment the {@link #getComment() comment}.
   */
  public ScoreCommentLine(String comment) {

    super(Collections.<ScoreCell<?>> emptyList());
    this.comment = comment;
  }

  /**
   * @return the comment line.
   */
  public String getComment() {

    return this.comment;
  }

  @Override
  public boolean isContinueRow() {

    return true;
  }

  @Override
  protected ScoreCell<?> createCell() {

    throw new IllegalStateException();
  }

  @Override
  public ScoreCommentLine transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }
}
