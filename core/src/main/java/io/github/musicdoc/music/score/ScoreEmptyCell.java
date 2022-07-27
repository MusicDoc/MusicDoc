package io.github.musicdoc.music.score;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreCell} for {@link ScoreEmptyLine} or {@link io.github.musicdoc.music.score.comment.ScoreCommentLine}. Will
 * never be instantiated. Only exists to satisfy Java's strict generic type system.
 */
public final class ScoreEmptyCell extends ScoreCell<ScoreEmptyCell> {

  private ScoreEmptyCell() {

    super();
  }

  @Override
  public ScoreEmptyCell copy(MutableObjecteCopier copier) {

    return this;
  }

  @Override
  public ScoreEmptyCell transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }

}
