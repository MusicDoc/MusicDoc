package io.github.musicdoc.music.instrument.keyboard;

import io.github.musicdoc.music.tone.Tone;

public class Piano extends KeyboardInstrument {

  private static final Tone LOWEST_TONE = Tone.A0;

  private static final Tone HIGHEST_TONE = Tone.C8;

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
