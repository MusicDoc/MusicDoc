package io.github.musicdoc.music.score.tab;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.transpose.TransposeContext;

public class ScoreTabLine extends ScoreLine<ScoreTabCell, ScoreTabLine> {

  public ScoreTabLine() {

    super();
  }

  private ScoreTabLine(ScoreTabLine line, MutableObjecteCopier copier) {

    super(line, copier);
  }

  @Override
  public ScoreTabLine copy(MutableObjecteCopier copier) {

    return new ScoreTabLine(this, copier);
  }

  public void addCell(ScoreTabCell cell) {

    this.cells.add(cell);
  }

  @Override
  protected ScoreTabCell createCell() {

    return new ScoreTabCell();
  }

  @Override
  public ScoreTabLine transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreTabLine transposed = new ScoreTabLine();
    for (ScoreTabCell cell : this.cells) {
      transposed.cells.add(cell.transpose(steps, diatonic, context));
    }
    return transposed;
  }
}
