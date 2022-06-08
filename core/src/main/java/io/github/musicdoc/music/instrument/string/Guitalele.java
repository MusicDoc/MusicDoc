package io.github.musicdoc.music.instrument.string;

import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.tone.Tone;

public class Guitalele extends Guitar {

    public static final Guitalele GUITALELE = new Guitalele(12);

    public Guitalele(int maxFret) {
        super(maxFret, Tone.B2, Tone.E3, Tone.A3, Tone.D4, Tone.FS4, Tone.B4);
    }

    @Override
    public String getName() {
        return "Guitalele";
    }

    @Override
    public ChromaticInterval getChordOffset() {
        return ChromaticInterval.PERFECT_FIFTH;
    }
}
