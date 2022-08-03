package io.github.musicdoc.instrument.brass;

import io.github.musicdoc.tone.Tone;

public class Trumpet extends PitchOfBrassInstrument {

    @Override
    public String getName() {
        return "Trumpet";
    }

    @Override
    public Tone getLowestTone() {
        return Tone.BF3;
    }
}
