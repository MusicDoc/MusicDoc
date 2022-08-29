package io.github.musicdoc.instrument.string;

import io.github.musicdoc.tone.Tone;

public abstract class FrettedStringInstrument extends StringInstrument {

  private final int maxFret;

  public FrettedStringInstrument(int maxFret, Tone... tuning) {

    super(tuning);
    this.maxFret = maxFret;
  }

  @Override
  public int getMaxFret() {

    return this.maxFret;
  }
}
