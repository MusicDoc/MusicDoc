package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.music.rythm.Fraction;

/**
 * The tempo indicates how fast the song shall be played. See
 * <a href="https://abcnotation.com/wiki/abc:standard:v2.1#qtempo">tempo in ABC</a> for details.
 */
public class Tempo {

  private final String prefix;

  private final Fraction[] fractions;

  private final int bpm;

  private final String suffix;

  /**
   * The constructor.
   *
   * @param prefix the {@link #getPrefix() prefix}.
   * @param bpm the #getBpm()
   * @param suffix the {@link #getSuffix() suffix}.
   * @param fractions the {@link #getFraction(int) fractions}.
   */
  public Tempo(String prefix, int bpm, String suffix, Fraction... fractions) {

    super();
    if (prefix == null) {
      this.prefix = "";
    } else {
      this.prefix = prefix.trim();
    }
    if ((fractions == null) || (fractions.length == 0)) {
      throw new IllegalArgumentException("Tempo fractions must not be empty.");
    }
    for (Fraction fraction : fractions) {
      if (fraction == null) {
        throw new IllegalArgumentException("Tempo fraction must not be null.");
      }
    }
    this.fractions = fractions;
    if (bpm <= 0) {
      throw new IllegalArgumentException("bpm=" + bpm);
    }
    this.bpm = bpm;
    if (suffix == null) {
      this.suffix = "";
    } else {
      this.suffix = suffix.trim();
    }
  }

  /**
   * @return the optional prefix (e.g. "Allegro"). Shall be empty if not present but not {@code null}.
   */
  public String getPrefix() {

    return this.prefix;
  }

  /**
   * @return the first {@link #getFraction(int) fraction}.
   */
  public Fraction getFraction() {

    return getFraction(0);
  }

  /**
   * @param i the index of the requested {@link Fraction}.
   * @return fractions
   */
  public Fraction getFraction(int i) {

    if ((i >= 0) && (i < this.fractions.length)) {
      return this.fractions[i];
    }
    return null;
  }

  /**
   * @return the number of {@link #getFraction(int) fractions} available. Typically {@code 1}.
   */
  public int getFractionCount() {

    return this.fractions.length;
  }

  /**
   * @return the number of beats per minute with one beat being the (sum of the) {@link #getFraction(int) fraction(s)}.
   */
  public int getBpm() {

    return this.bpm;
  }

  /**
   * @return the optional suffix (e.g. "Slowly"). Shall be empty if not present but not {@code null}.
   */
  public String getSuffix() {

    return this.suffix;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append(this.prefix);
    String space = "";
    if (!this.prefix.isEmpty()) {
      space = " ";
    }
    for (Fraction fraction : this.fractions) {
      sb.append(space);
      sb.append(fraction);
      space = " ";
    }
    sb.append('=');
    sb.append(this.bpm);
    if (!this.suffix.isEmpty()) {
      sb.append(' ');
    }
    sb.append(this.suffix);
    return sb.toString();
  }

}
