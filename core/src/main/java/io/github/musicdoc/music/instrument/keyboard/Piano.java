package io.github.musicdoc.music.instrument.keyboard;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

public class Piano extends KeyboardInstrument {

    private static final Tone LOWEST_TONE = Tone.of(TonePitchEnglish.A, -2);

    private static final Tone HIGHEST_TONE = Tone.of(TonePitchEnglish.C, 5);

    @Override
    public String getName() {
        return "Piano";
    }

    @Override
    public Tone getLowestTone() {
        return LOWEST_TONE;
    }

    @Override
    public Tone getHighestTone() {
        return HIGHEST_TONE;
    }
}
