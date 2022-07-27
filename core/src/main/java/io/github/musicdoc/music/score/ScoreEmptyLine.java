package io.github.musicdoc.music.score;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Implementation of {@link ScoreLine} representing an empty line with no content.
 */
public class ScoreEmptyLine extends ScoreLine<ScoreEmptyCell, ScoreEmptyLine> {

  /** The singleton instance. */
  public static final ScoreEmptyLine INSTANCE = new ScoreEmptyLine();

  private ScoreEmptyLine() {

    super();
    this.immutalbe = true;
  }

  @Override
  public ScoreEmptyLine copy(MutableObjecteCopier copier) {

    return this;
  }

  @Override
  protected ScoreEmptyCell createCell() {

    throw new IllegalStateException();
  }

  @Override
  public ScoreEmptyLine transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }

}
