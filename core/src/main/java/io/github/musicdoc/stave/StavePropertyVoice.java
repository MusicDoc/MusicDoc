package io.github.musicdoc.stave;

import java.util.List;

import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * {@link StaveProperty} for the first {@link StaveVoice} of the {@link Stave#getVoices() voices property}.
 */
class StavePropertyVoice extends StaveProperty<StaveVoice> {

  public static final StavePropertyVoice INSTANCE = new StavePropertyVoice();

  public StavePropertyVoice() {

    super("voice");
  }

  @Override
  public StaveVoice get(AbstractStave<?> stave) {

    if (!(stave instanceof Stave)) {
      return null;
    }
    List<StaveVoice> voices = ((Stave) stave).getVoices();
    int size = voices.size();
    if (size == 0) {
      return null;
    }
    return voices.get(size - 1);
  }

  @Override
  public AbstractStave<?> set(AbstractStave<?> stave, StaveVoice voice) {

    if (stave instanceof Stave) {
      stave = ((Stave) stave).addVoice(voice);
    }
    return stave;
  }
}
