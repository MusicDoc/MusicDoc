package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.music.score.line.ScoreLine;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents a row of a {@link Score}. Such row is a consists of a number of {@link #getLines() lines} that can form an
 * entire system of {@link io.github.musicdoc.music.stave.Stave}(s), lyrics, etc.
 *
 * @see ScoreSection#getRows()
 */
public class ScoreRow extends AbstractTransposable<ScoreRow> implements MutableObject<ScoreRow> {

  private List<ScoreLine> lines;

  private boolean immutable;

  /**
   * The constructor.
   */
  public ScoreRow() {

    super();
    this.lines = new ArrayList<>();
  }

  private ScoreRow(ScoreRow row, MutableObjecteCopier copier) {

    super();
    this.lines = copier.copyList(this.lines);
  }

  @Override
  public ScoreRow copy(MutableObjecteCopier copier) {

    return new ScoreRow(this, copier);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public ScoreRow makeImmutable() {

    if (!this.immutable) {
      this.lines = MutableObjecteHelper.makeImmutableRecursive(this.lines);
      this.immutable = true;
    }
    return this;
  }

  /**
   * @return the {@link List} of {@link ScoreLine}s.
   */
  public List<ScoreLine> getLines() {

    return this.lines;
  }

  /**
   * @param line the {@link ScoreLine} to add.
   */
  public void addLine(ScoreLine line) {

    this.lines.add(line);
  }

  /**
   * @param i the index of the requested {@link ScoreLine}.
   * @return the {@link ScoreLine} at the given index.
   * @see List#get(int)
   */
  public ScoreLine getLine(int i) {

    if (i >= this.lines.size()) {
      return null;
    }
    return this.lines.get(i);
  }

  /**
   * @return the number of columns, what is the maximum {@link ScoreLine#getCellCount() number of cells} for all
   *         {@link #getLines() lines}.
   */
  public int getColumnCount() {

    int columnCount = 0;
    for (ScoreLine line : this.lines) {
      int cellCount = line.getCellCount();
      if (cellCount > columnCount) {
        columnCount = cellCount;
      }
    }
    return columnCount;
  }

  @Override
  public ScoreRow transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreRow transposed = new ScoreRow();
    for (ScoreLine line : this.lines) {
      transposed.addLine(line.transpose(steps, diatonic, context));
    }
    return transposed;
  }

  @Override
  public void toString(StringBuilder sb) {

    for (ScoreLine line : this.lines) {
      line.toString(sb);
      sb.append('\n');
    }
  }
}
