package io.github.musicdoc.stave.activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Implementation of {@link StaveVoiceAcitvity} based on {@link StaveVoice#getId() voice ID} using
 * {@link ActivityListFilter}.
 */
public class StaveVoiceActivityVocieId extends ActivityListFilter<String> implements StaveVoiceAcitvity {

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param instrumentNames the {@link Instrument#getName() instrument names} to use as whitelist or blacklist.
   */
  public StaveVoiceActivityVocieId(boolean whiteList, Set<String> instrumentNames) {

    super(whiteList, instrumentNames);
  }

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param instrumentNames the {@link Instrument#getName() instrument names} to use as whitelist or blacklist.
   */
  public StaveVoiceActivityVocieId(boolean whiteList, String... instrumentNames) {

    this(whiteList, new HashSet<>(Arrays.asList(instrumentNames)));
  }

  @Override
  public boolean isActive(StaveVoice voice) {

    if (voice == null) {
      return false;
    }
    return isActive(voice.getId());
  }

}
