package io.github.musicdoc.music.instrument.brass;

import io.github.musicdoc.music.tone.Tone;

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
