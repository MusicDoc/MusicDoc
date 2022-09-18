package io.github.musicdoc.instrument.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.tone.Tone;

/**
 * {@link Instrument} with individual strings (e.g. violin, guitar, etc.).
 */
public abstract class StringInstrument extends Instrument implements StringTuning {

  private final List<Tone> tuning;

  private final Tone lowestTone;

  public StringInstrument(Tone... tuning) {

    super();
    this.tuning = Collections.unmodifiableList(Arrays.asList(tuning));
    Tone lowest = null;
    for (Tone tone : tuning) {
      if (tone.isLower(lowest)) {
        lowest = tone;
      }
    }
    this.lowestTone = lowest;
  }

  @Override
  public List<Tone> getStrings() {

    return this.tuning;
  }

  @Override
  public Tone getLowestTone() {

    return this.lowestTone;
  }
}
