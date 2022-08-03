package io.github.musicdoc.instrument.brass;

import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.tone.TonePitchEnglish;

public class TubaEs extends Tuba {

    @Override
    public Tone getLowestTone() {
        return Tone.of(TonePitchEnglish.E_FLAT, 2);
    }
}
