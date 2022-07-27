package io.github.musicdoc.music.score;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.AbstractTransposable;

/**
 * Abstract base class for a cell of a {@link ScoreLine}.
 *
 * @param <SELF> type of this class itself.
 * @see ScoreLine#getCell(int)
 */
public abstract class ScoreCell<SELF extends ScoreCell<SELF>> extends AbstractTransposable<SELF> implements MutableObject<SELF> {

  /** @see #getChordContainer() */
  protected ChordContainer chordContainer;

  /** @see #isImmutable() */
  protected boolean immutable;

  /**
   * The constructor.
   */
  public ScoreCell() {

    super();
  }

  /**
   * The {@link #copy()} constructor.
   *
   * @param cell the {@link ScoreCell} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected ScoreCell(ScoreCell<SELF> cell, MutableObjecteCopier copier) {

    super();
    this.chordContainer = cell.chordContainer;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public SELF makeImmutable() {

    this.immutable = true;
    return self();
  }

  /**
   * @return the {@link ChordContainer}.
   */
  public ChordContainer getChordContainer() {

    return this.chordContainer;
  }

  /**
   * @param chordContainer the new value of {@link #getChordContainer()}.
   * @return a new {@link ScoreCell} with the given {@link #getChordContainer() chordContainer} and all other properties
   *         like {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public SELF setChordContainer(ChordContainer chordContainer) {

    if (this.chordContainer == chordContainer) {
      return self();
    }
    SELF cell = makeMutable();
    cell.chordContainer = chordContainer;
    return cell;
  }

  /**
   * @return {@link ChordContainer#getChord()}
   */
  public Chord getChord() {

    if (this.chordContainer == null) {
      return null;
    }
    return this.chordContainer.getChord();
  }

  /**
   * @param chord the new value of {@link #getChord()}.
   * @return a new {@link Stave} with the given {@link #getChord() chord} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public SELF setChord(Chord chord) {

    return setChordContainer(new ChordContainer(chord));
  }

  /**
   * @return {@link ChordContainer#toString()}
   */
  public String getChordString() {

    if (this.chordContainer == null) {
      return "";
    }
    return this.chordContainer.toString();
  }
}
