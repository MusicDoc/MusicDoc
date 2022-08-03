package io.github.musicdoc.instrument;

import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.tone.Tone;

public abstract class Instrument {

  public abstract String getName();

  public abstract Tone getLowestTone();

  /**
   * @return the number of semitones the notes written in the stave are actually higher than they sound. Will be
   *         {@code 0} for a regular {@link Instrument}. Will e.g. be {@code 14} for alt sax. To note the
   *         {@link #getLowestTone() lowest tone} you need to subtract this offset (transpose the tone down by this
   *         offset).
   */
  public int getTransposingOffset() {

    return 0;
  }

  /**
   * @return {@code true} if this is a {@link #getTransposingOffset() transposing} intrument, {@code false} otherwise.
   */
  public final boolean isTransposing() {

    return getTransposingOffset() == 0;
  }

  public boolean isElectric() {

    return false;
  }

  public final boolean isAcoustic() {

    return !isElectric();
  }

  /**
   * @return the {@link ChromaticInterval} indicating the chromatic offset relative to the
   *         {@link io.github.musicdoc.score.Score} when playing {@link Chord}s. E.g. for a special
   *         {@link io.github.musicdoc.instrument.string.FrettedStringInstrument} this would be the Capo offset
   *         relative to the normal instrument. For instance a
   *         {@link io.github.musicdoc.instrument.string.Guitalele} has a chord offset of 7 chromatic steps higher
   *         than a regular {@link io.github.musicdoc.instrument.string.Guitar}.
   */
  public ChromaticInterval getChordOffset() {

    return ChromaticInterval.PERFECT_UNISON;
  }

  /**
   * @return the {@link ChromaticInterval} indicating the chromatic offset relative to the
   *         {@link io.github.musicdoc.score.Score} when playing {@link Chord}s. E.g.
   */
  public ChromaticInterval getToneOffset() {

    return ChromaticInterval.PERFECT_UNISON;
  }
}
