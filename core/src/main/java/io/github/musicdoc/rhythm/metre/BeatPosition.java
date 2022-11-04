package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.rhythm.fraction.Fraction;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.StaveChange;

/**
 * A {@link BeatPosition} represents the position within a score as {@link #getBar() bar number} and
 * {@link #getFraction() fraction within the current bar}.
 */
public class BeatPosition implements Comparable<BeatPosition> {

  private int bar;

  private double fraction;

  private Metre metre;

  private boolean barProcessed;

  /**
   * The constructor.
   */
  public BeatPosition() {

    this(1, Metre._4_4);
  }

  /**
   * The constructor with an initial bar. In case of complex music the may be different simplified scores for specific
   * musicians. In such case a sequence (here an intro) may be omitted if not relevant for the instrument or voice. In
   * order to ensure that all musicians can refer to the same {@link #getBar() bar numbers} this can be annotated in the
   * score.
   *
   * @param bar the initial {@link #getBar() bar number}. Shall be positive.
   * @param metre the initial {@link #getMetre() metre}.
   */
  public BeatPosition(int bar, Metre metre) {

    super();
    this.bar = bar;
    this.fraction = 0;
    if (metre == null) {
      this.metre = Metre._4_4;
    } else {
      this.metre = metre;
    }
  }

  /**
   * The copy-constructor.
   *
   * @param position the {@link BeatPosition} to copy.
   */
  public BeatPosition(BeatPosition position) {

    this();
    if (position != null) {
      this.bar = position.bar;
      this.fraction = position.fraction;
      this.metre = position.metre;
      this.barProcessed = position.barProcessed;
    }
  }

  /**
   * @return the bar number within the score. Will start from {@code 1} by default and increment with each completed bar
   *         per {@link io.github.musicdoc.stave.system.StaveSystem}.
   */
  public int getBar() {

    return this.bar;
  }

  /**
   * @return the current {@link Fraction#getValue() faction value} within the current bar. If it reaches the
   *         {@link #getMetre() metre}, it will be reset to {@code 0} and the {@link #getBar() bar number} will be
   *         incremented.
   */
  public double getFraction() {

    return this.fraction;
  }

  /**
   * @return the current {@link Metre} with the beat information.
   */
  public Metre getMetre() {

    return this.metre;
  }

  /**
   * @param metre new value of {@link #getMetre()}.
   */
  public void setMetre(Metre metre) {

    this.metre = metre;
  }

  /**
   * @param cell the {@link ScoreCell} to process and increment {@link #getBar() bar number} and {@link #getFraction()
   *        faction} accordingly.
   * @return {@code true} if the given {@link ScoreCell} is missing a closing {@link ScoreCell#getBar() bar line} or the
   *         bar was not matching the {@link #getMetre() metre} (excluding the first bar for pickup bar).
   */
  public boolean add(ScoreCell cell) {

    boolean validBar = true;
    StaveChange staveChange = cell.getStaveChange();
    if (staveChange != null) {
      Metre newMetre = staveChange.getMetre();
      if (newMetre != null) {
        assert (this.fraction == 0); // stave change has to follow immediately after bar, TODO move to BarLine?
        setMetre(newMetre);
      }
    }
    ValuedItem<?> item = cell.getItem();
    if (item == null) {
      return validBar;
    }
    MusicalValue value = item.getValue();
    if (value == null) {
      assert false : "value shall not be null";
      return true;
    }
    this.fraction += value.getValue();
    double rest = this.metre.getValue() - this.fraction;
    boolean hasBar = cell.getBar() != null;
    if (rest < 0.00001) {
      this.bar++;
      this.fraction = 0;
      // TODO we could split exceeding item automatically into two connected with a tie
      validBar = (rest > -0.00001) && hasBar;
    } else if (hasBar) {
      validBar = !this.barProcessed;
      this.bar++;
      this.fraction = 0;
    }
    if (hasBar) {
      this.barProcessed = true;
    }
    return validBar;
  }

  @Override
  public int compareTo(BeatPosition other) {

    if (other == null) {
      return -1;
    } else if (other != this) {
      double delta = (this.bar + this.fraction) - (other.bar + other.fraction);
      if (delta < -0.0000001) {
        return -1;
      } else if (delta > 0.0000001) {
        return 1;
      }
    }
    return 0;
  }

  /**
   * @param other the {@link BeatPosition} to {@link #compareTo(BeatPosition) compare to}.
   * @return {@code true} if {@code this} {@link BeatPosition} is after the given one, {@code false} otherwise.
   */
  public boolean isAfter(BeatPosition other) {

    return compareTo(other) > 0;
  }

  /**
   * @param other the {@link BeatPosition} to {@link #compareTo(BeatPosition) compare to}.
   * @return {@code true} if {@code this} {@link BeatPosition} is before the given one, {@code false} otherwise.
   */
  public boolean isBefore(BeatPosition other) {

    return compareTo(other) < 0;
  }

  /**
   * @param other the {@link BeatPosition} to {@link #compareTo(BeatPosition) compare to}.
   * @return {@code true} if {@code this} {@link BeatPosition} is equal to the given one, {@code false} otherwise.
   */
  public boolean isEqualTo(BeatPosition other) {

    return compareTo(other) == 0;
  }

}
