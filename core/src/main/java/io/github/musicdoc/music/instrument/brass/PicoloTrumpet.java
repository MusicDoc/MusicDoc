package io.github.musicdoc.music.instrument.brass;

import io.github.musicdoc.music.tone.Tone;

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
