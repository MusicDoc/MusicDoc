package io.github.musicdoc.music.instrument.brass;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

public class TubaEs extends Tuba {

    @Override
    public Tone getLowestTone() {
        return Tone.of(TonePitchEnglish.E_FLAT, 2);
    }
}
