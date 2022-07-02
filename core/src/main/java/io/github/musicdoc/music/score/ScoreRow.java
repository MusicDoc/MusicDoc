package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents a row of a {@link Score}. Such row is a consists of a number of {@link #getLines() lines} that can
 * form an entire system of {@link Stave}(s), lyrics, etc.
 *
 * @see ScoreSection#getRows()
 */
public class ScoreRow extends AbstractTransposable<ScoreRow> {

  private final List<ScoreLine<?, ?>> lines;

  /**
   * The constructor.
   */
  public ScoreRow() {

    super();
    this.lines = new ArrayList<>();
  }

  /**
   * @return the {@link List} of {@link ScoreLine}s.
   */
  public List<ScoreLine<?, ?>> getLines() {

    return this.lines;
  }

  /**
   * @param line the {@link ScoreLine} to add.
   */
  public void addLine(ScoreLine<?, ?> line) {

    this.lines.add(line);
  }

  /**
   * @param i the index of the requested {@link ScoreLine}.
   * @return the {@link ScoreLine} at the given index.
   * @see List#get(int)
   */
  public ScoreLine<?, ?> getLine(int i) {

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
    for (ScoreLine<?, ?> line : this.lines) {
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
    for (ScoreLine<?, ?> line : this.lines) {
      transposed.addLine(line.transpose(steps, diatonic, context));
    }
    return transposed;
  }
}
