package io.github.musicdoc.music.stave;

import java.util.List;

/**
 * {@link StaveProperty} for the first {@link StaveVoice} of the {@link Stave#getVoices() voices property}.
 */
class StavePropertyVoice extends StaveProperty<StaveVoice> {

  public static final StavePropertyVoice INSTANCE = new StavePropertyVoice();

  public StavePropertyVoice() {

    super("voice");
  }

  @Override
  public StaveVoice get(Stave stave) {

    if (stave == null) {
      return null;
    }
    List<StaveVoice> voices = stave.getVoices();
    int size = voices.size();
    if (size == 0) {
      return null;
    }
    return voices.get(size - 1);
  }

  @Override
  public void set(Stave stave, StaveVoice voice) {

    if (stave != null) {
      stave.addVoice(voice);
    }
  }
}
