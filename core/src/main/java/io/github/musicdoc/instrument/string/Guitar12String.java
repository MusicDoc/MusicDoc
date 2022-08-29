package io.github.musicdoc.instrument.string;

import io.github.musicdoc.tone.Tone;

public class Guitar12String extends Guitar {

  public static final Guitar12String GUITAR_12_STRING = new Guitar12String();

  public Guitar12String() {

    super(16, Tone.E4, Tone.E4, Tone.B3, Tone.B3, Tone.G3, Tone.G4, Tone.D3, Tone.D4, Tone.A2, Tone.A3, Tone.E2,
        Tone.E3);
  }

  @Override
  public String getName() {

    return "12-String Guitar";
  }
}
