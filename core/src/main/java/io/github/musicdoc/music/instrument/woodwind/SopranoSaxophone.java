package io.github.musicdoc.music.instrument.woodwind;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

public class SopranoSaxophone extends Saxophone {

  private static final Tone LOWEST_TONE = Tone.of(TonePitchEnglish.A_FLAT, 3);

  @Override
  public String getName() {

    return "Soprano Saxophone";
  }

  @Override
  public Tone getLowestTone() {

    return LOWEST_TONE;
  }
}
