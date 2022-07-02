package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.volta.Volta;

/**
 * A {@link BarLine} is a border in a stave as vertical bar(s). It terminates a couple of
 * {@link io.github.musicdoc.music.note.Note}s or {@link io.github.musicdoc.music.rythm.rest.Rest}s that complete a full
 * {@link io.github.musicdoc.music.rythm.beat.Beat}.
 *
 * @see BarLineType
 */
public class BarLine {

  private final BarLineType type;

  private final Volta volta;

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type}.
   */
  public BarLine(BarLineType type) {

    this(type, Volta.NONE);
  }

  /**
   * The constructor.
   *
   * @param type the {@link #getType() type}.
   * @param volta the {@link #getVolta() volta}.
   */
  public BarLine(BarLineType type, Volta volta) {

    super();
    this.type = type;
    this.volta = volta;
    // assert (volta == null) || !type.isRightBarOnly();
  }

  /**
   * @return the bar type.
   */
  public BarLineType getType() {

    return this.type;
  }

  /**
   * @return the {@link Volta}.
   */
  public Volta getVolta() {

    return this.volta;
  }

  @Override
  public String toString() {

    if (this.volta == Volta.NONE) {
      return this.type.getSymbol();
    }
    return this.type.getSymbol() + this.volta;
  }

}
