package io.github.musicdoc.harmony.chord;

import java.util.Objects;

import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * A simple container for a {@link #getChord() chord} in combination with the {@link #getSuffix() suffix}. This is
 * useful to preserve the original source information if the chord could not be parsed properly.
 *
 * @see #getChord()
 * @see #getSuffix()
 */
public class ChordContainer extends AbstractTransposable<ChordContainer> {

  static final ChordContainer EMPTY = new ChordContainer("");

  private final ChordSymbol chord;

  private final String suffix;

  private ChordContainer next;

  /**
   * The constructor.
   *
   * @param suffix the {@link #getSuffix() suffix}.
   */
  public ChordContainer(String suffix) {

    this(null, suffix);
  }

  /**
   * The constructor.
   *
   * @param chord the {@link #getChord() chord}.
   */
  public ChordContainer(ChordSymbol chord) {

    this(chord, "");
  }

  /**
   * The constructor.
   *
   * @param chord the {@link #getChord() chord}.
   * @param suffix the {@link #getSuffix() suffix}.
   */
  public ChordContainer(ChordSymbol chord, String suffix) {

    super();
    this.chord = chord;
    this.suffix = suffix;
  }

  /**
   * @return the parsed {@link ChordSymbol}. May be {@code null} if the parsed chord information did not contain a valid
   *         {@link ChordSymbol}. E.g. "N.C." or "tacet" may pragmatically be provided as "chord" but can not be represented
   *         by the class {@link ChordSymbol} as these examples are no valid chords.
   * @see #getSuffix()
   */
  public ChordSymbol getChord() {

    return this.chord;
  }

  /**
   * @return the prefix of the {@link ChordSymbol}. This may contain additional information (whitespaces, clutter) to preserve
   *         the full original representation from the source format. Further, this allows to preserve invalid chord
   *         information that can not be parsed as {@link ChordSymbol} such as "D.C." or "X". In the latter case the
   *         {@link #getChord() chord} would be {@code null}.
   * @see #getChord()
   */
  public String getSuffix() {

    return this.suffix;
  }

  /**
   * @return the next {@link ChordContainer}.
   */
  public ChordContainer getNext() {

    return this.next;
  }

  void setNext(ChordContainer next) {

    this.next = next;
  }

  @Override
  public ChordContainer transpose(int steps, boolean diatonic, TransposeContext context) {

    if (this.chord == null) {
      return this;
    }
    ChordSymbol transposedChord = this.chord.transpose(steps, diatonic, context);
    return new ChordContainer(transposedChord, this.suffix);
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.chord, this.suffix);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ChordContainer other = (ChordContainer) obj;
    return Objects.equals(this.chord, other.chord) && Objects.equals(this.next, other.next)
        && Objects.equals(this.suffix, other.suffix);
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.chord != null) {
      this.chord.toString(sb);
    }
    sb.append(this.suffix);
  }

  /**
   * @param chord the {@link #getChord() chord}.
   * @return the new {@link ChordContainer} or {@code null} if the given {@link ChordSymbol} is {@code null}.
   */
  public static ChordContainer of(ChordSymbol chord) {

    if (chord == null) {
      return null;
    }
    return new ChordContainer(chord);
  }

}
