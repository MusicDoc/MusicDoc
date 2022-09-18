package io.github.musicdoc.rhythm.tuplet;

import io.github.musicdoc.rhythm.fraction.PlainFraction;

/**
 * TODO hohwille This type ...
 *
 */
public class TupletContext {

  private final Tuplet tuplet;

  private int itemCount;

  /**
   * The constructor.
   *
   * @param tuplet the {@link Tuplet}.
   * @param noteCount the #getNoteCount
   */
  public TupletContext(Tuplet tuplet, int noteCount) {

    super();
    this.tuplet = tuplet;
    this.itemCount = noteCount;
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
  public int getItemCount() {

    return this.itemCount;
  }

  /**
   * Decrements the {@link #getItemCount() note count}. Shall only be called if {@link #getItemCount() note count} is
   * currently positive ({@code > 0}).
   *
   * @return the new decreased {@link #getItemCount() note count}.
   */
  public int decrementNoteCount() {

    assert (this.itemCount > 0);
    if (this.itemCount > 0) {
      this.itemCount--;
    }
    return this.itemCount;
  }

}
