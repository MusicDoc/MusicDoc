package io.github.musicdoc.instrument.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.tone.Tone;

public abstract class StringInstrument extends Instrument {

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

  public List<Tone> getTuning() {

    return this.tuning;
  }

  @Override
  public Tone getLowestTone() {

    return this.lowestTone;
  }
}
