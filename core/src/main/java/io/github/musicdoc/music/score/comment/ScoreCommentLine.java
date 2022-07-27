package io.github.musicdoc.music.score.comment;

import java.util.Collections;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.score.ScoreEmptyCell;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreLine} for a general purpose {@link #getComment() comment}.
 */
public class ScoreCommentLine extends ScoreLine<ScoreEmptyCell, ScoreCommentLine> {

  private String comment;

  /**
   * The constructor.
   *
   * @param comment the {@link #getComment() comment}.
   */
  public ScoreCommentLine(String comment) {

    super(Collections.emptyList());
    this.comment = comment;
  }

  private ScoreCommentLine(ScoreCommentLine line, MutableObjecteCopier copier) {

    super(line, copier);
    this.comment = line.comment;
  }

  @Override
  public ScoreCommentLine copy(MutableObjecteCopier copier) {

    return new ScoreCommentLine(this, copier);
  }

  /**
   * @return the comment line.
   */
  public String getComment() {

    return this.comment;
  }

  /**
   * @param comment new value of {@link #getComment()}.
   * @return a new {@link ScoreCommentLine} with the given {@link #getComment() comment} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCommentLine setComment(String comment) {

    if (Objects.equals(comment, this.comment)) {
      return this;
    }
    ScoreCommentLine line = makeMutable();
    line.comment = comment;
    return line;
  }

  @Override
  protected ScoreEmptyCell createCell() {

    throw new IllegalStateException();
  }

  @Override
  public ScoreCommentLine transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }
}
