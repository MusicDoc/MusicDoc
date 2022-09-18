package io.github.musicdoc.tab;

/**
 * The tablature representation of a {@link io.github.musicdoc.note.Note}.
 *
 * @see io.github.musicdoc.note.NoteTone#getTab()
 */
public final class TabTone {

  private final int string;

  private final int fret;

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string}.
   * @param fret the {@link #getFret() fret}.
   */
  public TabTone(int string, int fret) {

    super();
    if (string < 0) {
      throw new IllegalStateException(Integer.toString(string));
    }
    if (fret < 0) {
      throw new IllegalStateException(Integer.toString(fret));
    }
    this.string = string;
    this.fret = fret;
  }

  /**
   * @return the index of the {@link io.github.musicdoc.instrument.string.StringTuning#getString(int) string} to play.
   */
  public int getString() {

    return this.string;
  }

  /**
   * @return the fret to play on the specified {@link #getString() string}. Will be {@code 0} to play the open string or
   *         the number if the fret to press the string at whilst playing the string.
   *
   * @see io.github.musicdoc.instrument.string.StringTuning#getMaxFret()
   */
  public int getFret() {

    return this.fret;
  }

  @Override
  public int hashCode() {

    return 31 * this.string + this.fret;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (obj.getClass() != TabTone.class)) {
      return false;
    }
    TabTone other = (TabTone) obj;
    if (this.string != other.string) {
      return false;
    } else if (this.fret != other.fret) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    return this.fret + "@" + this.string;
  }

}
