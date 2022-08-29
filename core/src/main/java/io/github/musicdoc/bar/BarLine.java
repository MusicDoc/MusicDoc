package io.github.musicdoc.bar;

import java.util.Objects;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.volta.Volta;

/**
 * A {@link BarLine} is a border in a stave as vertical bar(s). It terminates a couple of
 * {@link io.github.musicdoc.rythm.value.ValuedItem}s ({@link io.github.musicdoc.note.Note}s or
 * {@link io.github.musicdoc.rythm.rest.Rest}s) that typically complete a full
 * {@link io.github.musicdoc.rythm.beat.Beat}.
 *
 * @see BarLineType
 */
public class BarLine extends AbstractMusicalObject {

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
  public int hashCode() {

    return Objects.hash(this.type, this.volta);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    BarLine other = (BarLine) obj;
    return Objects.equals(this.type, other.type) && Objects.equals(this.volta, other.volta);
  }

  @Override
  public void toString(StringBuilder sb) {

    this.type.toString(sb);
    this.volta.toString(sb);
  }

}
