/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.harmony.ChromaticInterval;
import io.github.musicdoc.music.harmony.EnharmonicStyle;
import io.github.musicdoc.music.harmony.Interval;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * A {@link TonePitch} represents a tone within a {@link MusicalKey#getChromaticScale() scale}. It is based on the
 * twelve tone music system and only represents a relative pitch within a single octave. If you want to represent an
 * absolute pitch value use {@link Tone} instead.
 */
public abstract class TonePitch extends AbstractTransposable<TonePitch> {

  /** @see #getName() */
  protected final String name;

  /** @see #getStep() */
  protected final ChromaticStep step;

  /** @see #getEnharmonicStyle() */
  protected final EnharmonicType enharmonicType;

  /** @see #getCase() */
  protected final ToneNameCase nameCase;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param step the {@link #getStep() step}.
   * @param nameCase the {@link ToneNameCase}.
   * @param enharmonicType the {@link EnharmonicType}.
   */
  protected TonePitch(String name, ChromaticStep step, ToneNameCase nameCase, EnharmonicType enharmonicType) {

    super();
    assert ((name != null) && (!name.isEmpty()));
    this.name = name;
    assert (step != null);
    this.step = step;
    assert (nameCase.convert(name).equals(name));
    this.nameCase = nameCase;
    assert (enharmonicType != null);
    this.enharmonicType = enharmonicType;
    assert (getNameStyle().checkName(name, enharmonicType)) : name + getNameStyle();
  }

  /**
   * @return the number of half-tone steps upwards from {@link TonePitchEnglish#C} in the range from
   *         {@code 0}-{@code 11}.
   */
  public ChromaticStep getStep() {

    return this.step;
  }

  /**
   * @return the name of this pitch.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the {@link ToneNameStyle} of this pitch.
   */
  public abstract ToneNameStyle<?> getNameStyle();

  /**
   * @return the {@link EnharmonicStyle} of this pitch.
   * @see #isFlat()
   * @see #isNormal()
   * @see #isSharp()
   */
  public EnharmonicStyle getEnharmonicStyle() {

    return this.enharmonicType.getStyle();
  }

  /**
   * @return the {@link EnharmonicType} of this pitch.
   */
  public EnharmonicType getEnharmonicType() {

    return this.enharmonicType;
  }

  /**
   * @return the reference {@link TonePitch} this pitch is derived from. All {@link TonePitch}es of the same
   *         {@link #getNameStyle() name style} with the same {@link #getStep() step} are just alternate
   *         {@link #isLowercase() cases}, or {@link #getEnharmonicStyle() enharmonic changes} of the semantically same
   *         pitch. Therefore they all share the same reference what is the {@link TonePitch} with capital
   *         {@link #getCase() case} and in {@link #isNormal() normal} form. E.g. {@code Fb}, {@code fb}, {@code D##},
   *         {@code d##}, and {@code e} will all have the same reference which is {@code E}.
   */
  public abstract TonePitch getReference();

  /**
   * @return the number of accidental signs of this pitch (like 'b' to flatten or '#' to sharpen).
   */
  public int getSignCount() {

    return this.enharmonicType.getSignCount();
  }

  /**
   * @return {@code true} if this {@link TonePitch} requires a single or double sharp sign, {@code false} otherwise.
   */
  public boolean isSharp() {

    return EnharmonicStyle.SHARP.equals(this.enharmonicType.getStyle());
  }

  /**
   * @return {@code true} if this {@link TonePitch} requires a single or double flat sign, {@code false} otherwise.
   */
  public boolean isFlat() {

    return EnharmonicStyle.FLAT.equals(this.enharmonicType.getStyle());
  }

  /**
   * @return the sharpened {@link TonePitch} or {@code null} if no such pitch exists.
   * @see EnharmonicType#sharpen()
   */
  public TonePitch sharpen() {

    EnharmonicType newType = this.enharmonicType.sharpen();
    if (newType == null) {
      return null;
    }
    return getNameStyle().pitch(this.step.next(), newType, this.nameCase);
  }

  /**
   * @return the flattened {@link TonePitch} or {@code null} if no such pitch exists.
   * @see EnharmonicType#flatten()
   */
  public TonePitch flatten() {

    EnharmonicType newType = this.enharmonicType.flatten();
    if (newType == null) {
      return null;
    }
    return getNameStyle().pitch(this.step.previous(), newType, this.nameCase);
  }

  /**
   * @param nameStyle the {@link ToneNameStyle} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getReference() reference} and {@link #getCase() case} as this
   *         pitch but with the given {@link ToneNameStyle}.
   * @param <P> type of the {@link TonePitch}.
   */
  public <P extends TonePitch> P with(ToneNameStyle<P> nameStyle) {

    return with(nameStyle, this.nameCase);
  }

  /**
   * @param nameStyle the {@link ToneNameStyle} of the requested pitch.
   * @param newCase the {@link #getCase() case} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getReference() reference} and {@link #getStep() step} as this
   *         pitch but with the given {@link #getCase() case} and {@link #getNameStyle() name style}.
   * @param <P> type of the {@link TonePitch}.
   */
  public <P extends TonePitch> P with(ToneNameStyle<P> nameStyle, ToneNameCase newCase) {

    return nameStyle.pitch(this.step, this.enharmonicType, newCase);
  }

  /**
   * @param newCase the {@link #getCase() case} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getReference() reference} and {@link #getStep() step} as this
   *         pitch but with the given {@link #getCase() case}.
   */
  public abstract TonePitch with(ToneNameCase newCase);

  /**
   * @param newType the {@link #getEnharmonicType() enharmonic type} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getStep() step} and {@link #getCase() case} as this pitch but
   *         with the given {@link EnharmonicType}.
   */
  public TonePitch with(EnharmonicType newType) {

    return with(newType, this.nameCase);
  }

  /**
   * @param newType the {@link #getEnharmonicType() enharmonic type} of the requested pitch.
   * @param newCase the {@link #getCase() case} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getStep() step} as this pitch but with the given
   *         {@link #getCase() case} and (if possible) the given {@link EnharmonicType}.
   */
  public TonePitch with(EnharmonicType newType, ToneNameCase newCase) {

    return with(getNameStyle(), newType, newCase);
  }

  /**
   * @param newStyle the new {@link #getEnharmonicStyle() enharmonic style} of the requested pitch.
   * @param newType the {@link #getEnharmonicType() enharmonic type} of the requested pitch.
   * @param newCase the {@link #getCase() case} of the requested pitch.
   * @return a {@link TonePitch} with the same {@link #getStep() step} as this pitch but with the given
   *         {@link #getCase() case} and (if possible) the given {@link EnharmonicType}.
   * @param <P> type of the {@link TonePitch}.
   */
  public <P extends TonePitch> P with(ToneNameStyle<P> newStyle, EnharmonicType newType, ToneNameCase newCase) {

    return newStyle.pitch(this.step, newType, newCase);
  }

  /**
   * @return the normal form of this {@link TonePitch} in case this is an enharmonic change. In case this
   *         {@link TonePitch} itself if it is already {@link #isNormal() normal} this method will return this instance
   *         itself.
   */
  public TonePitch getNormalForm() {

    return getNameStyle().pitch(this.step, null, this.nameCase);
  }

  /**
   * @return {@code true} if this is the {@link #getNormalForm() normal form} (that would be used in
   *         {@link MusicalKey#C_MAJOR}), {@code false} in case of an <em>enharmonic change</em>.
   */
  public boolean isNormal() {

    return (getNormalForm() == this);
  }

  /**
   * @return the {@link ToneNameCase} of this {@link TonePitch}.
   */
  public ToneNameCase getCase() {

    return this.nameCase;
  }

  /**
   * @return {@code true} if the {@link #getName() name} is entirely in {@link String#toLowerCase() lower case},
   *         {@code false} otherwise (the first letter is {@link Character#isUpperCase(char) upper case}).
   */
  public boolean isLowercase() {

    return (this.nameCase == ToneNameCase.LOWER_CASE);
  }

  /**
   * @param targetTone is the target {@link TonePitch}.
   * @return the {@link ChromaticInterval} to {@link #transpose(Interval, TransposeContext) get} from this
   *         {@link TonePitch} to the given {@code targetTone}.
   */
  public ChromaticInterval getInterval(TonePitch targetTone) {

    int interval = targetTone.step.get() - this.step.get();
    if (interval < 0) {
      interval = interval + 12;
    }
    return ChromaticInterval.of(interval);
  }

  @Override
  public TonePitch transpose(int steps, boolean diatonic, TransposeContext context) {

    MusicalKey key = null;
    EnharmonicStyle style = EnharmonicStyle.NORMAL;
    TonalSystem system = null;
    if (context != null) {
      key = context.getKey();
      style = context.getEnharmonicStyle();
      system = context.getTonalSystem();
    }
    if (key == null) {
      if (diatonic) {
        if (system != null) {
          int chromaticSteps = system.getChromaticSteps(steps);
          if (chromaticSteps != Integer.MIN_VALUE) {
            return transposeChromatic(chromaticSteps, style);
          }
        }
        // can not transpose properly without key, using fallback is better than exception
        return transposeDiatonic(steps, MusicalKey.C_MAJOR);
      } else {
        return transposeChromatic(steps, style);
      }
    } else if (diatonic) {
      return transposeDiatonic(steps, key);
    } else {
      return transposeChromatic(steps, key);
    }
  }

  /**
   * @param semitoneSteps the number of semitones to transpose upwards. Use negative value to transpose down.
   * @param style the {@link EnharmonicStyle} to use.
   * @return the transposed {@link TonePitch}.
   */
  public TonePitch transposeChromatic(int semitoneSteps, EnharmonicStyle style) {

    return transposeChromatic(semitoneSteps, style, true);
  }

  /**
   * @param semitoneSteps the number of semitones to transpose upwards. Use negative value to transpose down.
   * @param style the {@link EnharmonicStyle} to use.
   * @param strict - {@code true} for strict transposing (e.g. prevent {@link TonePitchEnglish#B_FLAT} for
   *        {@link EnharmonicStyle#SHARP}), {@code false} otherwise.
   * @return the transposed {@link TonePitch}.
   */
  public TonePitch transposeChromatic(int semitoneSteps, EnharmonicStyle style, boolean strict) {

    ChromaticStep targetStep = this.step.add(semitoneSteps);
    TonePitch result;
    if (strict) {
      result = getNameStyle().pitch(targetStep, this.nameCase, style);
    } else {
      result = getNameStyle().pitch(targetStep, null, this.nameCase);
      EnharmonicStyle enharmonicStyle = result.getEnharmonicStyle();
      if (enharmonicStyle.isNormal() || (enharmonicStyle == style)) {
        return result;
      }
      result = getNameStyle().pitch(targetStep, this.nameCase, style);
    }
    return result;
  }

  /**
   * @param semitoneSteps the number of semitones to transpose upwards. Use negative value to transpose down.
   * @param key the {@link MusicalKey} to use for diatonic transposing.
   * @return the transposed {@link TonePitch}.
   */
  public TonePitch transposeChromatic(int semitoneSteps, MusicalKey key) {

    ChromaticStep targetStep = this.step.add(semitoneSteps - key.getTonika().getStep().get());
    return key.getChromaticScale().get(targetStep.get()).with(getNameStyle(), this.nameCase);
  }

  /**
   * @param diatonicSteps the number of diatonic steps to transpose upwards. Use negative value to transpose down.
   * @param key the {@link MusicalKey} to use for diatonic transposing.
   * @return the transposed {@link TonePitch}.
   */
  public TonePitch transposeDiatonic(int diatonicSteps, MusicalKey key) {

    ChromaticStep chromaticTonikaOffset = this.step.add(-key.getTonika().getStep().get());
    int keySteps = key.getSystem().getDiatonicSteps(chromaticTonikaOffset.get(), false);
    DiatonicStep targetStep = DiatonicStep.of(keySteps).add(diatonicSteps);
    return key.getDiatonicScale().get(targetStep.get()).with(getNameStyle(), this.nameCase);
  }

  /**
   * @param pitch the {@link TonePitch} to compare.
   * @return {@code true} if this {@link TonePitch} and the given {@link TonePitch} are equal (have the same
   *         {@link #getStep() step}), {@code false} otherwise.
   */
  public boolean isEqualTo(TonePitch pitch) {

    if (pitch == null) {
      return false;
    }
    return (this.step == pitch.step);
  }

  @Override
  public String toString() {

    return this.name;
  }

}
