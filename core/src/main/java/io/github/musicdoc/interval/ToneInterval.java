/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.interval;

import io.github.musicdoc.harmony.TonalSystem;

/**
 * Interface for an {@link ToneInterval} that defines the distance from one
 * {@link io.github.musicdoc.tone.TonePitch} or {@link io.github.musicdoc.tone.Tone} to another.
 */
public interface ToneInterval {

  /**
   * @see #getChromaticSteps(TonalSystem)
   * @return the number of semitone steps relative to the initial {@link io.github.musicdoc.tone.TonePitch}
   *         (tonika) or {@code null} if undefined (e.g. for {@link Solmization} or {@link DiatonicStep}.
   */
  default Integer getChromaticSteps() {

    int chromaticSteps = getChromaticSteps(null);
    if (chromaticSteps == Integer.MIN_VALUE) {
      return null;
    }
    return chromaticSteps;
  }

  /**
   * Returns the number of chromatic semitone steps from the initial
   * {@link io.github.musicdoc.tone.TonePitch}.<br/>
   * <b>ATTENTION:</b><br/>
   * This means the result is {@code 0}-based, so e.g. for {@link ChromaticInterval#PERFECT_FOURTH} it will return
   * {@code 5}.
   *
   * @param system the underlying {@link TonalSystem}. Instead of providing {@code null} it is recommended to use
   *        {@link #getChromaticSteps()} instead.
   * @return the number of semitone steps relative to the initial {@link io.github.musicdoc.tone.TonePitch}
   *         (tonika) or {@link Integer#MIN_VALUE} if undefined (e.g. for {@link Solmization} if {@link TonalSystem} is
   *         {@code null} or in case of a {@link DiatonicInterval}). The modulo of twelve ({@code %12}) of the result is
   *         the {@link java.util.List#get(int) index} of the {@link io.github.musicdoc.tone.TonePitch} from the
   *         {@link io.github.musicdoc.harmony.key.MusicalKey#getChromaticScale() chromatic scale} of the
   *         {@link io.github.musicdoc.harmony.key.MusicalKey} with the initial
   *         {@link io.github.musicdoc.tone.TonePitch} as
   *         {@link io.github.musicdoc.harmony.key.MusicalKey#getTonika() tonika}.
   */
  int getChromaticSteps(TonalSystem system);

  /**
   * @see #getDiatonicSteps(TonalSystem)
   * @return the number of diatonic steps relative to the initial {@link io.github.musicdoc.tone.TonePitch}
   *         (tonika) or {@code null} if undefined (e.g. for {@link Solmization} or {@link DiatonicStep}. <br>
   *         <b>ATTENTION:</b><br>
   *         The result is {@code 0}-based. See {@link #getDiatonicSteps(TonalSystem)} for details.
   */
  default Integer getDiatonicSteps() {

    int diatonicSteps = getDiatonicSteps(null);
    if (diatonicSteps == Integer.MIN_VALUE) {
      return null;
    }
    return diatonicSteps;
  }

  /**
   * @param system the underlying {@link TonalSystem}. Instead of providing {@code null} it is recommended to use
   *        {@link #getDiatonicSteps()} instead.
   * @return the number of diatonic steps relative to the initial {@link io.github.musicdoc.tone.TonePitch}
   *         (tonika) or {@link Integer#MIN_VALUE} if {@link TonalSystem} is {@code null} and the result is undefined
   *         (e.g. for {@link Solmization}). The modulo of seven ({@code %7}) of the result is the
   *         {@link java.util.List#get(int) index} of the {@link io.github.musicdoc.tone.TonePitch} from the
   *         {@link io.github.musicdoc.harmony.key.MusicalKey#getDiatonicScale() diatonic scale} of the
   *         {@link io.github.musicdoc.harmony.key.MusicalKey} with the initial
   *         {@link io.github.musicdoc.tone.TonePitch} as
   *         {@link io.github.musicdoc.harmony.key.MusicalKey#getTonika() tonika}.<br>
   *         <b>ATTENTION:</b><br>
   *         This means the result is {@code 0}-based, so e.g. for {@link DiatonicInterval#THIRD} it will return
   *         {@code 2} (NOT {@code 3}) and for {@link DiatonicInterval#OCTAVE} it will return {@code 7} (NOT {@code 8}).
   *         Music has evolved a very long time ago and is historically grown. Modern match with the concept of zero
   *         ({@code 0}) as the neutral element of addition, might have not been well-known when musical intervals have
   *         been defined. However, this is causing a lot of confusion: Many people know that to reach the same pitch in
   *         the next octave you have to add 12 semitones as semitone steps are defined zero-based. However, most people
   *         think that to reach the same pitch in the next octave you add eight diatonic steps as octave comes from the
   *         Latin number 8. As you need to go only 7 diatonic steps (starting from c you go 7 pitches up for an octave:
   *         d,e,f,g,a,b,c) this is causing a lot of confusion. As a result to lower the
   *         {@link io.github.musicdoc.tone.Tone}s in a {@link io.github.musicdoc.stave.Stave} by one
   *         octave, you write a small 8 below the {@link io.github.musicdoc.clef.Clef} symbol. Now to lower by
   *         two octaves you need to annotate a 15 instead of the 8 what makes you think twice before you understand the
   *         concept. Finally, when you
   *         {@link io.github.musicdoc.tone.Tone#transpose(ToneInterval, io.github.musicdoc.music.transpose.TransposeContext)
   *         transpose} a {@link io.github.musicdoc.tone.Tone} by adding a primum (Latin for {@code 1}.), you get
   *         the same tone back whereas in math adding 1 would actually increase the value.<br>
   *         As a conclusion we can avoid all the mess and confusion by following best practices from math and
   *         computer-science by technically defining diatonic steps also zero-based like already established by
   *         everyone for diatonic steps. This is causing consistency and avoiding pitfalls and bugs though we can not
   *         avoid the confusion already present by the established music theory and have to stick to it when rendering
   *         scores.
   */
  int getDiatonicSteps(TonalSystem system);

  /**
   * @return the total number of octaves included in this interval to go up (if positive) or down (if negative).
   */
  int getOctaves();

  /**
   * @return {@code true} if the empty interval such as e.g. {@value ChromaticInterval#PERFECT_UNISON},
   *         {@link DiatonicInterval#UNISON}, or {@link DiatonicStep#S0}. <b>ATTENTION:</b> will return {@code false}
   *         for {@link Solmization#DO} as for {@link TonalSystem#MINOR} it is not an empty interval.
   */
  boolean isEmpty();

  /**
   * @return the inverted {@link ToneInterval} so it will transpose in the opposite direction.
   */
  ToneInterval invert();

}
