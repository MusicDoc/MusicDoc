package io.github.musicdoc.instrument.string;

import io.github.musicdoc.tone.Tone;

public class Guitar extends FrettedStringInstrument {

  public static final Guitar GUITAR = new Guitar(16);

  protected Guitar(int maxFret, Tone... tuning) {

    super(maxFret, tuning);
  }

  public Guitar(int maxFret) {

    super(maxFret, Tone.E4, Tone.B3, Tone.G3, Tone.D3, Tone.A2, Tone.E2);
  }

  @Override
  public String getName() {

    return "Guitar";
  }
}
