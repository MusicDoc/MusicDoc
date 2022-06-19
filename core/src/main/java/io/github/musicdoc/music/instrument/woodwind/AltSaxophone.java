package io.github.musicdoc.music.instrument.woodwind;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Alt {@link Saxophone}.
 */
public class AltSaxophone extends Saxophone {

  private static final Tone LOWEST_TONE = Tone.of(TonePitchEnglish.D_FLAT, 3);

  @Override
  public String getName() {

    return "Alt Saxophone";
  }

  @Override
  public Tone getLowestTone() {

    return LOWEST_TONE;
  }
}
