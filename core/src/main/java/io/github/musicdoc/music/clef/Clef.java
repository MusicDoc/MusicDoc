/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.clef;

import java.util.Objects;

import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsClefs;
import io.github.musicdoc.music.glyphs.MusicalGlyphs;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsClefs;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.interval.DiatonicInterval;
import io.github.musicdoc.music.interval.Interval;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * The clef is the initial symbol of a {@link io.github.musicdoc.music.stave.Stave} that indicates which line is
 * identifying which {@link Tone}.
 */
public class Clef implements MusicalGlyphs, ClefObject {

  /** Regular {@link ClefSymbol#G G-clef}. */
  public static final Clef TREBLE = new Clef(ClefSymbol.G);

  /** Regular {@link ClefSymbol#F F-clef}. */
  public static final Clef BASS = new Clef(ClefSymbol.F);

  /** Regular {@link ClefSymbol#C C-clef}. */
  public static final Clef ALTO = new Clef(ClefSymbol.C);

  /** {@link ClefSymbol#C C-clef} for tenor ({@link #ALTO} shifted up 2 diatonic tones). */
  public static final Clef TENOR = new Clef(ClefSymbol.C, DiatonicInterval.THIRD);

  /** Regular {@link ClefSymbol#PERCUSSION_1 percussion clef 1}. */
  public static final Clef PERCUSSION_1 = new Clef(ClefSymbol.PERCUSSION_1);

  /** Regular {@link ClefSymbol#PERCUSSION_2 percussion clef 2}. */
  public static final Clef PERCUSSION_2 = new Clef(ClefSymbol.PERCUSSION_2);

  private final ClefSymbol symbol;

  private final Interval shift;

  /**
   * The constructor.
   *
   * @param symbol the {@link #getSymbol() clef symbol}.
   * @param shift the {@link #getShift() shift}.
   */
  private Clef(ClefSymbol symbol) {

    this(symbol, ChromaticInterval.PERFECT_UNISON);
  }

  /**
   * The constructor.
   *
   * @param symbol the {@link #getSymbol() clef symbol}.
   * @param shift the {@link #getShift() shift}.
   */
  private Clef(ClefSymbol symbol, Interval shift) {

    super();
    this.symbol = symbol;
    this.shift = shift;
  }

  /**
   * @return the {@link ClefSymbol}.
   */
  public ClefSymbol getSymbol() {

    return this.symbol;
  }

  /**
   * @return the {@link Interval} to shift the {@link ClefSymbol}. Typically {@code 0}. E.g. a value of {@code +8}
   *         shifts the clef one octave up and a value of {@code -8} shifts the clef one octave down.
   */
  public Interval getShift() {

    return this.shift;
  }

  @Override
  public Tone getReferenceTone() {

    return this.symbol.getReferenceTone();
  }

  @Override
  public Tone getLowTone() {

    Tone low = this.symbol.getLowTone();
    if (low == null) {
      return null;
    }
    if (this.shift != ChromaticInterval.PERFECT_UNISON) {
      TransposeContext context = new TransposeContext(MusicalKey.C_MAJOR);
      low = low.transpose(this.shift, context);
    }
    return low;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    // should we actually combine the clef with the key to resolve this perfectly?
    int chromaticShift = this.shift.getChromaticSteps(TonalSystem.MAJOR);
    if (chromaticShift == Integer.MIN_VALUE) {
      int diatonicSteps = this.shift.getDiatonicSteps(TonalSystem.MAJOR);

    }
    if (context.isEnforceUnicode()) {
      return UnicodeGlyphsClefs.get(this.symbol, chromaticShift);
    } else {
      return SmuflGlyphsClefs.get(this.symbol, chromaticShift);
    }
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (obj.getClass() != Clef.class)) {
      return false;
    }
    Clef other = (Clef) obj;
    if (this.symbol != other.symbol) {
      return false;
    } else if (!this.shift.equals(other.shift)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.symbol, this.shift);
  }

  @Override
  public String toString() {

    String result = this.symbol.toString();
    int steps = this.shift.getChromaticSteps(TonalSystem.MAJOR);
    if (steps > 0) {
      result = result + "+" + steps;
    } else if (steps < 0) {
      result = result + steps;
    }
    return result;
  }

  /**
   * @param type the {@link ClefSymbol}.
   * @param shift the {@link #getShift() shift}.
   * @return the according {@link Clef}.
   */
  public static Clef of(ClefSymbol type, Interval shift) {

    return new Clef(type, shift);
  }

}
