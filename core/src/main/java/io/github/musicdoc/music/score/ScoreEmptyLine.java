package io.github.musicdoc.music.score;

import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Implementation of {@link ScoreLine} representing an empty line with no content.
 */
public class ScoreEmptyLine extends ScoreLine<ScoreCell<?>, ScoreEmptyLine> {

  /** The singleton instance. */
  public static final ScoreEmptyLine INSTANCE = new ScoreEmptyLine();

  private ScoreEmptyLine() {

    super();
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
  public ScoreEmptyLine transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }

}
