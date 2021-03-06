/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.harmony.chord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.tone.ToneNameCase;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * A {@link Chord} is a combination of {@link TonePitch}es played simultaneously. Typical {@link Chord}s have a
 * {@link #getTonalSystem() tonal system} which is either {@link TonalSystem#MAJOR} or {@link TonalSystem#MINOR}. Such
 * {@link Chord}s consists of a least three tones:
 * <ul>
 * <li>the {@link #getFundamental() fundamental tone}</li>
 * <li>the {@link ChromaticInterval#MINOR_THIRD} on the {@link #getFundamental() fundamental tone} for
 * {@link TonalSystem#MINOR} or the {@link ChromaticInterval#MAJOR_THIRD} on the {@link #getFundamental() fundamental
 * tone} for {@link TonalSystem#MAJOR}.</li>
 * <li>the {@link ChromaticInterval#PERFECT_FIFTH} on the {@link #getFundamental() fundamental tone}.</li>
 * </ul>
 * Simple examples:
 * <ul>
 * <li>the chord {@link #toString() named} "C" stands for {@link io.github.musicdoc.music.tone.TonePitchEnglish#C C}-
 * {@link TonalSystem#MAJOR major}) and is composed of {@link io.github.musicdoc.music.tone.TonePitchEnglish#C C},
 * {@link io.github.musicdoc.music.tone.TonePitchEnglish#E E}, and
 * {@link io.github.musicdoc.music.tone.TonePitchEnglish#G G}.</li>
 * <li>the chord {@link #toString() named} "cm" stands for {@link io.github.musicdoc.music.tone.TonePitchEnglish#C c}-
 * {@link TonalSystem#MINOR minor}) and is composed of {@link io.github.musicdoc.music.tone.TonePitchEnglish#C C},
 * {@link io.github.musicdoc.music.tone.TonePitchEnglish#D_SHARP D#}, and
 * {@link io.github.musicdoc.music.tone.TonePitchEnglish#G G}.</li>
 * </ul>
 * Further, a {@link Chord} can have {@link #getExtensions() extensions} that {@link ChordExtension#getIntervals() add}
 * or {@link ChordExtension#isRemoveThird() remove} tones from the {@link Chord}. A very common example is
 * {@link ChordExtension#_7} that adds a {@link ChromaticInterval#MINOR_SEVENTH minor seventh} relative to the
 * {@link #getFundamental() fundamental tone} to the {@link Chord}. However, there can also be complex combinations such
 * as "Csus4no5add11". Also there are the special {@link ChordExtension extensions} {@link ChordExtension#DIM} and
 * {@link ChordExtension#AUG} that change the chord character totally. <br/>
 * Finally, a {@link Chord} can have a different {@link #getBase() base tone}. This tone is not only added but has to be
 * the lowest tone in the {@link Chord}.<br/>
 * {@link Chord}s can be inverted what means that the tones except for a {@link #getBase() base tone} (if different from
 * {@link #getFundamental() fundamental tone}) can be {@link ChromaticInterval#PERFECT_OCTAVE octavated} so the order of
 * the tones from lower to higher tones will change. Instances of this {@link Chord} class do not distinguish inversions
 * of chords.
 */
public class Chord extends AbstractTransposable<Chord> {

  private final TonePitch fundamentalTone;

  private final TonalSystem tonalSystem;

  private final TonePitch baseTone;

  private final List<ChordExtension> extensions;

  private final String name;

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   */
  public Chord(TonePitch fundamental, TonalSystem system) {

    this(fundamental, system, null, Collections.EMPTY_LIST);
  }

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   * @param base - see {@link #getBase()}.
   */
  public Chord(TonePitch fundamental, TonalSystem system, TonePitch base) {

    this(fundamental, system, base, Collections.EMPTY_LIST);
  }

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   * @param extensions - see {@link #getExtensions()}.
   */
  public Chord(TonePitch fundamental, TonalSystem system, ChordExtension... extensions) {

    this(fundamental, system, null, extensions);
  }

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   * @param base - see {@link #getBase()}.
   * @param extensions - see {@link #getExtensions()}.
   */
  public Chord(TonePitch fundamental, TonalSystem system, TonePitch base, ChordExtension... extensions) {

    this(fundamental, system, base, Arrays.asList(extensions));
  }

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   * @param base - see {@link #getBase()}.
   * @param extensions - see {@link #getExtensions()}.
   */
  public Chord(TonePitch fundamental, TonalSystem system, TonePitch base, List<ChordExtension> extensions) {

    this(Collections.unmodifiableList(extensions), fundamental, system, base);
  }

  /**
   * The constructor.
   *
   * @param fundamental - see {@link #getFundamental()}.
   * @param system - see {@link #getTonalSystem()}.
   * @param base - see {@link #getBase()}.
   * @param extensions - see {@link #getExtensions()}.
   */
  private Chord(List<ChordExtension> extensions, TonePitch fundamental, TonalSystem system, TonePitch base) {

    super();
    Objects.requireNonNull(fundamental, "fundamental");
    Objects.requireNonNull(extensions, "extensions");
    this.fundamentalTone = fundamental;
    this.tonalSystem = system;
    if (base == null) {
      this.baseTone = fundamental;
    } else {
      this.baseTone = base;
    }
    this.extensions = extensions;
    this.name = buildName();
  }

  /**
   * @return the normalized instance of this {@link Chord}. E.g. for "Dismajor4/Es" the normalized result would be "D#4"
   *         and for "C??/fis" this method would return "Cmaj7/F#".
   */
  public Chord normalize() {

    TonePitch fundamental = this.fundamentalTone.getNormalForm();
    TonalSystem system = this.tonalSystem;
    if (system != null) {
      if (system.isMinor()) {
        system = TonalSystem.MINOR_M;
        fundamental = this.fundamentalTone.with(ToneNameCase.LOWER_CASE);
      } else if (system.isMajor()) {
        system = TonalSystem.MAJOR_EMPTY;
        fundamental = this.fundamentalTone.with(ToneNameCase.CAPITAL_CASE);
      } else {
        system = system.getReference();
        fundamental = this.fundamentalTone.with(ToneNameCase.CAPITAL_CASE);
      }
    }
    List<ChordExtension> extList = new ArrayList<>(this.extensions.size());
    for (ChordExtension ext : this.extensions) {
      if (ext.isRemoveThird()) {
        system = null;
      }
      extList.add(ext.getReference());
    }
    TonePitch base = fundamental;
    if (hasBase()) {
      base = this.baseTone.getReference();
    }
    return new Chord(extList, fundamental, system, base);
  }

  private String buildName() {

    StringBuilder sb = new StringBuilder();
    sb.append(this.fundamentalTone.getName());
    if (this.tonalSystem != null) {
      sb.append(this.tonalSystem.getName());
    }
    for (ChordExtension ext : this.extensions) {
      sb.append(ext.getName());
    }
    if (hasBase()) {
      sb.append('/');
      sb.append(this.baseTone.getName());
    }
    return sb.toString();
  }

  /**
   * @return the name of this chord. This is also the {@link #toString() string representation}.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return {@link TonalSystem#MAJOR}, {@link TonalSystem#MINOR}. May be {@code null} (e.g. for "sus4", "aug" or "dim")
   */
  public TonalSystem getTonalSystem() {

    return this.tonalSystem;
  }

  /**
   * @return the fundamental tone is the {@link TonePitch} that defines the chord that is typically build by two thirds
   *         (mainly a minor and a major where their order distinguishes between {@link TonalSystem#MINOR} and
   *         {@link TonalSystem#MAJOR}).
   * @see #getBase()
   */
  public TonePitch getFundamental() {

    return this.fundamentalTone;
  }

  /**
   * @return {@code true} if this {@link Chord} has a {@link #getBase() base tone} different from its
   *         {@link #getFundamental() fundamental tone}.
   */
  public boolean hasBase() {

    return (!this.fundamentalTone.isEqualTo(this.baseTone));
  }

  /**
   * @return the base tone that is the lowest {@link TonePitch} played for this {@link Chord}. For a "regular"
   *         {@link Chord} the base is the same as the {@link #getFundamental() fundamental tone} (e.g. in case "Cm7"
   *         both base and fundamental are "C"). If the base differs from the {@link #getFundamental() fundamental}
   *         (e.g. in case of "Cm7/E" where base is "E" and fundamental is "C"), the base tone is added below the
   *         {@link #getFundamental() fundamental tone} or depending on the way it is played on a specific instrument it
   *         may replace the {@link #getFundamental() fundamental}.
   */
  public TonePitch getBase() {

    return this.baseTone;
  }

  /**
   * @return the {@link List} of {@link ChordExtension}s. May be {@link List#isEmpty() empty} but never {@code null}.
   */
  public List<ChordExtension> getExtensions() {

    return this.extensions;
  }

  @Override
  public Chord transpose(int steps, boolean diatonic, TransposeContext context) {

    TonePitch transposedFundamental = this.fundamentalTone.transpose(steps, diatonic, context);
    int chromaticSteps;
    if (diatonic) {
      // we need to transpose the base chromatic so that in case of diatonic transposing the chromatic
      // interval from the base to the fundamental does not change.
      chromaticSteps = getChromaticSteps(this.fundamentalTone, transposedFundamental, steps);
    } else {
      chromaticSteps = steps;
    }
    TransposeContext subContext = context;
    if (context.isNormalizeChords()) {
      subContext = null;
    }
    TonePitch transposedBase = this.baseTone.transpose(chromaticSteps, false, subContext);
    return new Chord(this.extensions, transposedFundamental, this.tonalSystem, transposedBase);
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.name);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    Chord other = (Chord) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    return this.name;
  }

}
