/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.harmony;

/**
 * Interface for an {@link Interval} that defines the distance from one {@link io.github.musicdoc.music.tone.TonePitch}
 * or {@link io.github.musicdoc.music.tone.Tone} to another.
 */
public interface Interval {

  /**
   * Returns the number of chromatic semitone steps from the initial
   * {@link io.github.musicdoc.music.tone.TonePitch}.<br/>
   * <b>ATTENTION:</b><br/>
   * This means the result is {@code 0}-based, so e.g. for {@link ChromaticInterval#PERFECT_FOURTH} it will return
   * {@code 5}.
   *
   * @param system the underlying {@link TonalSystem}. May be {@code null}.
   * @return the number of semitone steps relative to the initial {@link io.github.musicdoc.music.tone.TonePitch}
   *         (tonika) or {@link Integer#MIN_VALUE} if undefined (e.g. for {@link Solmization} if {@link TonalSystem} is
   *         {@code null} or in case of a {@link DiatonicInterval}). The modulo of twelve ({@code %12}) of the result is
   *         the {@link java.util.List#get(int) index} of the {@link io.github.musicdoc.music.tone.TonePitch} from the
   *         {@link MusicalKey#getChromaticScale() chromatic scale} of the {@link MusicalKey} with the initial
   *         {@link io.github.musicdoc.music.tone.TonePitch} as {@link MusicalKey#getTonika() tonika}.
   */
  int getChromaticSteps(TonalSystem system);

  /**
   * Returns the number of diatonic steps from the initial {@link io.github.musicdoc.music.tone.TonePitch}.<br/>
   * <b>ATTENTION:</b><br/>
   * This means the result is {@code 0}-based, so e.g. for {@link DiatonicInterval#THIRD} it will return {@code 2} (NOT
   * {@code 3}) and for {@link DiatonicInterval#OCTAVE} it will return {@code 7} (NOT {@code 8}). Music has evolved a
   * very long time ago and is historically grown. Modern match with the concept of zero ({@code 0}) as the neutral
   * element of addition, might have not been well-known when musical intervals have been defined. However, this is
   * causing a lot of confusion: Many people know that to reach the same pitch in the next octave you have to add 12
   * semitones. However, most people think that to reach the same pitch in the next octave you add eight diatonic steps
   * as octave comes from the Latin number 8. As you need to go only 7 diatonic steps (starting from c you go 7 pitches
   * up for an octave: d,e,f,g,a,b,c) this is causing a lot of confusion. As a result to lower the
   * {@link io.github.musicdoc.music.tone.Tone}s in a {@link io.github.musicdoc.music.stave.Stave} by one octave, you
   * write a small 8 below the {@link io.github.musicdoc.music.clef.Clef} symbol. Now to lower by two octaves you need
   * to annotate a 15 instead of the 8 what makes you think twice before you understand the concept. Finally, when you
   * {@link io.github.musicdoc.music.tone.Tone#transpose(Interval, io.github.musicdoc.music.transpose.TransposeContext)
   * transpose} a {@link io.github.musicdoc.music.tone.Tone} by adding a primum (Latin for {@code 1}.), you get the same
   * tone back whereas in math adding 1 would actually increase the value.<br>
   * As a conclusion we can avoid all the mess and confusion by following best practices from math and computer-science
   * by technically defining diatonic steps also zero-based like already established by everyone for diatonic steps.
   * This is causing consistency and avoiding pitfalls and bugs though we can not avoid the confusion already present by
   * the established music theory and have to stick to it when rendering scores.
   *
   * @param system the underlying {@link TonalSystem}. May be {@code null}.
   * @return the number of diatonic steps relative to the initial {@link io.github.musicdoc.music.tone.TonePitch}
   *         (tonika) or {@link Integer#MIN_VALUE} if undefined (e.g. for {@link Solmization} if {@link TonalSystem} is
   *         {@code null}). The modulo of seven ({@code %7}) of the result is the {@link java.util.List#get(int) index}
   *         of the {@link io.github.musicdoc.music.tone.TonePitch} from the {@link MusicalKey#getDiatonicScale()
   *         diatonic scale} of the {@link MusicalKey} with the initial {@link io.github.musicdoc.music.tone.TonePitch}
   *         as {@link MusicalKey#getTonika() tonika}.
   */
  int getDiatonicSteps(TonalSystem system);

}
