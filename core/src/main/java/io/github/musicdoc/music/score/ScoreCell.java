package io.github.musicdoc.music.score;

import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.transpose.AbstractTransposable;

/**
 * Abstract base class for a cell of a {@link ScoreLine}.
 *
 * @param <SELF> type of this class itself.
 * @see ScoreLine#getCell(int)
 */
public abstract class ScoreCell<SELF extends ScoreCell<SELF>> extends AbstractTransposable<SELF> {

  /** @see #getChordContainer() */
  protected ChordContainer chordContainer;

  /**
   * @return the {@link ChordContainer}.
   */
  public ChordContainer getChordContainer() {

    return this.chordContainer;
  }

  /**
   * @param chordContainer the new value of {@link #getChordContainer()}.
   */
  public void setChordContainer(ChordContainer chordContainer) {

    this.chordContainer = chordContainer;
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
   */
  public void setChord(Chord chord) {

    this.chordContainer = new ChordContainer(chord);
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
