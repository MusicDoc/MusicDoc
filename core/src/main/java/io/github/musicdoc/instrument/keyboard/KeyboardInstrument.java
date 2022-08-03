package io.github.musicdoc.instrument.keyboard;

import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.tone.Tone;

public abstract class KeyboardInstrument extends Instrument {

  public abstract Tone getHighestTone();

}
