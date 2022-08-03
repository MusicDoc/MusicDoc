package io.github.musicdoc.instrument.brass;

import io.github.musicdoc.tone.Tone;

public class PicoloTrumpet extends Trumpet {

    @Override
    public String getName() {
        return "Picolo Trumpet";
    }

    @Override
    public Tone getLowestTone() {
        return Tone.BF4;
    }
}
