package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.transpose.AbstractTransposable;

/**
 * Abstract base class for a line of a {@link ScoreRow}.
 *
 * @param <C> type of the contained {@link ScoreCell}s.
 * @param <SELF> this type itself.
 * @see io.github.musicdoc.music.score.voice.ScoreVoiceLine
 */
public abstract class ScoreLine<C extends ScoreCell<C>, SELF extends ScoreLine<C, SELF>> extends AbstractTransposable<SELF>
    implements MutableObject<SELF> {

  /** @see #getCells() */
  protected List<C> cells;

  /** @see #isImmutable() */
  protected boolean immutalbe;

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
   * The {@link #copy()} constructor.
   *
   * @param line the {@link ScoreLine} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected ScoreLine(ScoreLine<C, SELF> line, MutableObjecteCopier copier) {

    super();
    this.cells = copier.copyList(line.cells);
  }

  @Override
  public boolean isImmutable() {

    return this.immutalbe;
  }

  @Override
  public SELF makeImmutable() {

    if (!this.immutalbe) {
      this.cells = MutableObjecteHelper.makeImmutableRecursive(this.cells);
      this.immutalbe = true;
    }
    return self();
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
   * @return the {@link StaveVoice} this line is assigned to in case of a
   *         {@link io.github.musicdoc.music.score.voice.ScoreVoiceLine}.
   */
  public StaveVoice getVoice() {

    return null;
  }

}
