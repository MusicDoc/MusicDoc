package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.score.voice.ScoreVoiceLine;
import io.github.musicdoc.music.transpose.AbstractTransposable;

/**
 * Abstract base class for a line of a {@link ScoreRow}.
 *
 * @param <C> type of the contained {@link ScoreCell}s.
 * @param <SELF> this type itself.
 * @see ScoreVoiceLine
 */
public abstract class ScoreLine<C extends ScoreCell<?>, SELF extends ScoreLine<C, SELF>>
    extends AbstractTransposable<SELF> {

  /** @see #getCells() */
  protected final List<C> cells;

  /**
   * The constructor.
   */
  public ScoreLine() {

    super();
    this.cells = new ArrayList<>();
  }

  /**
   * The constructor.
   *
   * @param cells the {@link #getCells() cells}.
   */
  public ScoreLine(List<C> cells) {

    super();
    this.cells = cells;
  }

  /**
   * @return the {@link List} of {@link ScoreCell}s.
   */
  public List<C> getCells() {

    return this.cells;
  }

  /**
   * @return the number of {@link #getCell(int) cells} in this line.
   */
  public int getCellCount() {

    return this.cells.size();
  }

  /**
   * @param i the index of the requested {@link ScoreCell}.
   * @return the requested {@link ScoreCell} or {@code null} if undefined or the given index is less or equal to
   *         {@link #getCellCount() cell count}.
   */
  public C getCell(int i) {

    if (i >= this.cells.size()) {
      return null;
    }
    return this.cells.get(i);
  }

  /**
   * @param i the index of the requested {@link ScoreCell}.
   * @return the requested {@link ScoreCell}. If it did not previously exist, it has been created. The
   *         {@link #getCellCount() cell count} is automatically increased as needed.
   */
  public C getOrCreateCell(int i) {

    int size = this.cells.size();
    int delta = i - size;
    while (delta >= 0) {
      this.cells.add(createCell());
      delta--;
    }
    return this.cells.get(i);
  }

  /**
   * @return a new and empty {@link ScoreCell}.
   */
  protected abstract C createCell();

  /**
   * @return {@code true} if this line belongs to the same {@link ScoreRow}, {@code false} otherwise (if this is the
   *         first line of a new {@link ScoreRow}).
   */
  public abstract boolean isContinueRow();

}
