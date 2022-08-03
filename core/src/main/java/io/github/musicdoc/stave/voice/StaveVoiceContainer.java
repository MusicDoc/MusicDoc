package io.github.musicdoc.stave.voice;

/**
 * Interface for a container of {@link StaveVoice}s.
 */
public interface StaveVoiceContainer {

  /**
   * @param id the {@link StaveVoice#getId() ID} of the requested {@link StaveVoice}.
   * @return the {@link StaveVoice} with the given {@link StaveVoice#getId() ID} or {@code null} if no such
   *         {@link StaveVoice} exists.
   */
  StaveVoice getVoice(String id);

  /**
   * @param index the index of the requested {@link StaveVoice}.
   * @return the {@link StaveVoice} at the given {@code index} or {@code null} if no such {@link StaveVoice} exists.
   */
  StaveVoice getVoice(int index);

  /**
   * @return the number of {@link StaveVoice}s contained in this object.
   * @see #getVoice(int)
   * @see #indexOf(String)
   */
  int getVoiceCount();

  /**
   * @param id the {@link StaveVoice#getId() ID} of the {@link StaveVoice} to find the index of.
   * @return the {@link #getVoice(int) index} of the {@link StaveVoice} with the given {@link StaveVoice#getId() ID} or
   *         {@code -1} if no such {@link StaveVoice} exists.
   */
  int indexOf(String id);

}
