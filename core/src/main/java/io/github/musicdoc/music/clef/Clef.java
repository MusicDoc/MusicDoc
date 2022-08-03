/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.clef;

import java.util.Objects;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.glyphs.MusicalGlyphs;
import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsClefs;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsClefs;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.interval.ToneInterval;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * The clef is the initial symbol of a {@link io.github.musicdoc.music.stave.Stave} that indicates which line is
 * identifying which {@link Tone}.
 */
public class Clef extends AbstractMusicalObject implements MusicalGlyphs, ClefObject, MutableObject<Clef> {

  /** Regular {@link ClefSymbol#G G-clef}. */
  public static final Clef G = ofInteral(ClefSymbol.G, "g");

  /** {@link ClefSymbol#G G-clef} named as "treble". */
  public static final Clef TREBLE = ofInteral(ClefSymbol.G, "treble");

  /** Regular {@link ClefSymbol#F F-clef}. */
  public static final Clef F = ofInteral(ClefSymbol.F, "f");

  /** {@link ClefSymbol#F F-clef} named as "bass". */
  public static final Clef BASS = ofInteral(ClefSymbol.F, "bass");

  /** Regular {@link ClefSymbol#C C-clef}. */
  public static final Clef C = ofInteral(ClefSymbol.C, "c");

  /** {@link ClefSymbol#C C-clef} named as "alto". */
  public static final Clef ALTO = ofInteral(ClefSymbol.C, "alto");

  /** {@link ClefSymbol#C C-clef} for tenor ({@link #ALTO} moved up 2 diatonic tones). */
  public static final Clef TENOR = ofInteral(ClefSymbol.C, "tenor", ChromaticInterval.of(-3));

  /**
   * {@link ClefSymbol#C C-clef} for sopran ({@link #ALTO} moved down 4 diatonic tones).<br>
   * <b>ATTENTION:</b><br>
   * Do not get confused with {@link #TREBLE} what is the most common {@link Clef} used in modern music.
   */
  public static final Clef SOPRANO = ofInteral(ClefSymbol.C, "soprano", ChromaticInterval.PERFECT_FIFTH);

  /** {@link ClefSymbol#C C-clef} for tenor ({@link #ALTO} moved up 4 diatonic tones). */
  public static final Clef BARITONE = ofInteral(ClefSymbol.C, "baritone", ChromaticInterval.of(-7));

  /** {@link ClefSymbol#C C-clef} for tenor ({@link #ALTO} moved down 2 diatonic tones). */
  public static final Clef MEZZO_SOPRANO = ofInteral(ClefSymbol.C, "mezzosoprano", ChromaticInterval.MAJOR_THIRD);

  /** Regular {@link ClefSymbol#PERCUSSION_1 percussion clef 1}. */
  public static final Clef PERCUSSION_1 = ofInteral(ClefSymbol.PERCUSSION_1, "prec");

  /** Regular {@link ClefSymbol#PERCUSSION_2 percussion clef 2}. */
  public static final Clef PERCUSSION_2 = ofInteral(ClefSymbol.PERCUSSION_2, "perc2");

  /** Regular {@link ClefSymbol#PERCUSSION_2 percussion clef 2}. */
  public static final Clef NONE = ofInteral(null, "none");

  private ClefSymbol symbol;

  private ToneInterval shift;

  private String name;

  private Tone middleTone;

  private Tone referenceTone;

  private boolean immutable;

  private Clef(ClefSymbol symbol, String name) {

    this(symbol, name, ChromaticInterval.PERFECT_UNISON);
  }

  private Clef(ClefSymbol symbol, String name, ToneInterval shift) {

    super();
    this.symbol = symbol;
    this.shift = shift;
    this.name = name;
  }

  private Clef(Clef clef, MutableObjecteCopier copier) {

    super();
    this.symbol = clef.symbol;
    this.shift = clef.shift;
    this.name = clef.name;
    this.middleTone = clef.middleTone;
    this.referenceTone = clef.referenceTone;
  }

  @Override
  public Clef copy(MutableObjecteCopier copier) {

    return new Clef(this, copier);
  }

  /**
   * @return the {@link ClefSymbol}.
   */
  public ClefSymbol getSymbol() {

    return this.symbol;
  }

  /**
   * @param clefSymbol the new {@link #getSymbol() symbol}.
   * @return a {@link Clef} with the given {@link #getSymbol() symbol} and all other properties like {@code this} one.
   *         Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Clef setSymbol(ClefSymbol clefSymbol) {

    if (clefSymbol == this.symbol) {
      return this;
    }
    Clef clef = makeMutable();
    clef.symbol = clefSymbol;
    clef.middleTone = null;
    clef.referenceTone = null;
    return clef;
  }

  /**
   * @return the {@link ToneInterval} to shift the {@link ClefSymbol}. Typically
   *         {@link ChromaticInterval#PERFECT_UNISON} meaning no shift. E.g. a value of
   *         {@link ChromaticInterval#PERFECT_OCTAVE} shifts the {@link Clef} one octave up and
   *         {@link ChromaticInterval#of(int) ChromaticInterval.of(-12)} shifts the {@link Clef} one octave down.
   *         <b>ATTENTION:</b> If the shift is not a multiple of 12 then not only octaves are shifted and thus the
   *         {@link ClefSymbol} itself gets shifted in the {@link io.github.musicdoc.music.stave.Stave} relative to its
   *         default position. However, please be aware that the shift applies to the {@link Tone}s such as the
   *         {@link #getMiddleTone() middle tone}. The {@link ClefSymbol} actually gets shifted in the opposite
   *         direction. E.g. a {@link Clef#TENOR tenor-clef} uses {@link ClefSymbol#C C-clef} (like the {@link Clef#ALTO
   *         alto-clef}). However, the reference tone {@link Tone#C4 C4} is on the fourth instead of the third line.
   *         Therefore, the tone written on the third line in {@link Clef#ALTO alto-clef} is now {@link Tone#C4 A4}
   *         instead of {@link Tone#C4 C4} so the shift is a {@link ChromaticInterval#MINOR_THIRD} <b>down</b> while the
   *         {@link ClefSymbol#C C-clef} has to be shifted the same amount but upwards.
   */
  public ToneInterval getShift() {

    return this.shift;
  }

  /**
   * @param newShift the new {@link #getShift() shift}.
   * @return a {@link Clef} with the given {@link #getShift() shift} and all other properties like {@code this} one.
   *         Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Clef setShift(ToneInterval newShift) {

    if (Objects.equals(newShift, this.shift)) {
      return this;
    }
    Clef clef = makeMutable();
    clef.shift = newShift;
    clef.middleTone = null;
    clef.referenceTone = null;
    return clef;
  }

  /**
   * @param delta the {@link ToneInterval} to add to the {@link #getShift() clef shift}.
   * @return a {@link Clef} with the {@link #getShift() shift} added by the given {@link ToneInterval} and all other
   *         properties like {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Clef setShiftAdd(ToneInterval delta) {

    if (delta.isEmpty()) {
      return this;
    }
    TransposeContext context = new TransposeContext(MusicalKey.C_MAJOR);
    Tone newMiddle = getMiddleTone().transpose(delta, context);
    ChromaticInterval newShift = this.symbol.getMiddleTone().getInterval(newMiddle);
    return setShift(newShift);
  }

  /**
   * @return the name of this {@link Clef}.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param newName the new {@link #getName() name}.
   * @return a {@link Clef} with the given {@link #getName() name} and all other properties like {@code this} one. Will
   *         be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Clef setName(String newName) {

    if (Objects.equals(newName, this.name)) {
      return this;
    }
    Clef clef = makeMutable();
    clef.name = newName;
    return clef;
  }

  @Override
  public Tone getReferenceTone() {

    if ((this.referenceTone == null) && (this.symbol != null)) {
      Tone reference = this.symbol.getReferenceTone();
      int octaves = this.shift.getOctaves();
      if (octaves != 0) {
        reference = Tone.of(reference.getPitch(), reference.getOctave() + octaves);
      }
      this.referenceTone = reference;
    }
    return this.referenceTone;
  }

  @Override
  public Tone getMiddleTone() {

    if ((this.middleTone == null) && (this.symbol != null)) {
      Tone middle = this.symbol.getMiddleTone();
      if (this.shift != ChromaticInterval.PERFECT_UNISON) {
        TransposeContext context = new TransposeContext(MusicalKey.C_MAJOR);
        middle = middle.transpose(this.shift, context);
      }
      this.middleTone = middle;
    }
    return this.middleTone;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public Clef makeImmutable() {

    this.immutable = true;
    return this;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    // should we actually combine the clef with the key to resolve this perfectly?
    int chromaticShift = this.shift.getChromaticSteps(TonalSystem.MAJOR);
    assert (chromaticShift != Integer.MIN_VALUE);
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
    } else if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.symbol, this.shift);
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.symbol == null) {
      sb.append("none");
    } else {
      sb.append(this.symbol);
    }
    int steps = this.shift.getChromaticSteps(TonalSystem.MAJOR);
    if (steps > 0) {
      sb.append('+');
      sb.append(steps);
    } else if (steps < 0) {
      sb.append(steps);
    }
  }

  private static Clef ofInteral(ClefSymbol type, String name) {

    return new Clef(type, name).makeImmutable();
  }

  private static Clef ofInteral(ClefSymbol type, String name, ToneInterval shift) {

    return new Clef(type, name, shift).makeImmutable();
  }

  /**
   * @param type the {@link ClefSymbol}.
   * @param name the {@link #getName() name}.
   * @param shift the {@link #getShift() shift}.
   * @return the according {@link Clef}.
   */
  public static Clef of(ClefSymbol type, String name, ToneInterval shift) {

    return new Clef(type, name, shift);
  }

}
