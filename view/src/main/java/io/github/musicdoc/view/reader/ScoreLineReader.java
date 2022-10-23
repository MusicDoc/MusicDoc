package io.github.musicdoc.view.reader;

import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLine;

/**
 * Reader for {@link ScoreLine} with status pointing to reading-position.
 */
public class ScoreLineReader {

  private final ScoreLine line;

  private final BeatPosition position;

  private int cellIndex;

  /**
   * The constructor.
   *
   * @param line the {@link ScoreLine} to read.
   * @param position the optional existing {@link BeatPosition}.
   */
  public ScoreLineReader(ScoreLine line, BeatPosition position) {

    super();
    this.line = line;
    this.position = new BeatPosition(position);
  }

  /**
   * @return the {@link BeatPosition}.
   */
  public BeatPosition getPosition() {

    return this.position;
  }

  /**
   * @return the current {@link ScoreCell} or {@code null} if the end of this line has been reached.
   */
  public ScoreCell getCell() {

    return this.line.getCell(this.cellIndex);
  }

  /**
   * Gets and consumes the {@link #getCell() current cell}. So if a non-{@code null} {@link ScoreCell} is returned, this
   * method will also increment the cell index and {@link #getPosition() position}.
   *
   * @return the {@link #getCell() current cell} or {@code null} if the end of this line has been reached.
   */
  public ScoreCell getNext() {

    ScoreCell cell = this.line.getCell(this.cellIndex);
    if (cell != null) {
      this.cellIndex++;
      this.position.add(cell);
    }
    return cell;
  }

  /**
   * @return the {@link ScoreLine} the view is rendered from.
   */
  public ScoreLine getLine() {

    return this.line;
  }

}
