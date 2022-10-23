package io.github.musicdoc.stave.activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Implementation of {@link StaveVoiceAcitvity} based on {@link Instrument#getName() instrument name} using
 * {@link ActivityListFilter}.
 */
public class StaveVoiceActivityInstrumentName extends ActivityListFilter<String> implements StaveVoiceAcitvity {

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param instrumentNames the {@link Instrument#getName() instrument names} to use as whitelist or blacklist.
   */
  public StaveVoiceActivityInstrumentName(boolean whiteList, Set<String> instrumentNames) {

    super(whiteList, instrumentNames);
  }

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param instrumentNames the {@link Instrument#getName() instrument names} to use as whitelist or blacklist.
   */
  public StaveVoiceActivityInstrumentName(boolean whiteList, String... instrumentNames) {

    this(whiteList, new HashSet<>(Arrays.asList(instrumentNames)));
  }

  @Override
  public boolean isActive(StaveVoice voice) {

    if (voice == null) {
      return false;
    }
    Instrument instrument = voice.getInstrument();
    String instrumentName = null;
    if (instrument != null) {
      instrumentName = instrument.getName();
    }
    return isActive(instrumentName);
  }

}
