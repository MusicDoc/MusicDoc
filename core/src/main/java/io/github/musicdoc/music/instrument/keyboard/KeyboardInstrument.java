package io.github.musicdoc.music.instrument.keyboard;

import io.github.musicdoc.music.instrument.Instrument;
import io.github.musicdoc.music.tone.Tone;

public abstract class KeyboardInstrument extends Instrument {

  public abstract Tone getHighestTone();

}
