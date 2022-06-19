package io.github.musicdoc.music.instrument.woodwind;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Tenor {@link Saxophone}.
 */
public class TenorSaxophone extends Saxophone {

  private static final Tone LOWEST_TONE = Tone.of(TonePitchEnglish.A_FLAT, 1); // written as Bb2

  @Override
  public String getName() {

    return "Tenor Saxophone";
  }

  @Override
  public Tone getLowestTone() {

    return LOWEST_TONE;
  }
}
