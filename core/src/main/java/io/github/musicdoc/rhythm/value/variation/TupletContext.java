package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.rhythm.fraction.PlainFraction;

/**
 * TODO hohwille This type ...
 *
 */
public class TupletContext {

  private final Tuplet tuplet;

  private int noteCount;

  /**
   * The constructor.
   *
   * @param tuplet the {@link Tuplet}.
   * @param noteCount the #getNoteCount
   */
  public TupletContext(Tuplet tuplet, int noteCount) {

    super();
    this.tuplet = tuplet;
    this.noteCount = noteCount;
  }

  /**
   * @return the {@link PlainFraction} of this {@link TupletContext}. E.g. "3/2" for a tripplet.
   */
  public Tuplet getTuplet() {

    return this.tuplet;
  }

  /**
   * @return the number of notes (left) to group as tuplet.
   */
  public int getNoteCount() {

    return this.noteCount;
  }

  /**
   * Decrements the {@link #getNoteCount() note count}. Shall only be called if {@link #getNoteCount() note count} is
   * currently positive ({@code > 0}).
   */
  public void decrementNoteCount() {

    assert (this.noteCount > 0);
    if (this.noteCount > 0) {
      this.noteCount--;
    }
  }

}
