package io.github.musicdoc.music.transpose;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.interval.ToneInterval;
import io.github.musicdoc.music.tone.TonePitch;

/**
 * Abstract base implementation of {@link Transposable}.
 *
 * @param <SELF> this type itself.
 */
public abstract class AbstractTransposable<SELF extends Transposable<SELF>> extends AbstractMusicalObject
    implements Transposable<SELF> {

  @Override
  public SELF transposeChromatic(int steps) {

    return transpose(steps, false, new TransposeContext());
  }

  @Override
  public SELF transposeDiatonic(int steps) {

    return transpose(steps, true, new TransposeContext());
  }

  @Override
  public SELF transpose(ToneInterval interval, TransposeContext context) {

    TonalSystem tonalSystem = context.getTonalSystem();
    int steps = interval.getChromaticSteps(tonalSystem);
    if (steps != Integer.MIN_VALUE) {
      return transpose(steps, false, context);
    } else {
      steps = interval.getDiatonicSteps(tonalSystem);
      if (steps == Integer.MIN_VALUE) {
        throw new IllegalStateException("Can not transpose by " + interval);
      }
      return transpose(steps, true, context);
    }
  }

  /**
   * @param original the original {@link TonePitch}.
   * @param transposed the transposed {@link TonePitch}.
   * @param diatonicSteps the number diatonic steps for tranposing.
   * @return the number of chromatic steps from {@code original} to {@code transposed}.
   */
  protected int getChromaticSteps(TonePitch original, TonePitch transposed, int diatonicSteps) {

    int chromaticSteps = transposed.getStep().get() - original.getStep().get();
    if ((chromaticSteps < 0) && (diatonicSteps > 0)) {
      chromaticSteps = chromaticSteps + 12;
    } else if ((chromaticSteps > 0) && (diatonicSteps < 0)) {
      chromaticSteps = 12 - chromaticSteps;
    }
    return chromaticSteps;
  }

}
