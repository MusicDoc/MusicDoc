package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link StaveProperty} for the {@link Stave#getBeat() beat property}.
 */
class StavePropertyBeat extends StaveProperty<Beat> {

  public static final StavePropertyBeat INSTANCE = new StavePropertyBeat();

  public StavePropertyBeat() {

    super("beat");
  }

  @Override
  public Beat get(AbstractStave<?> stave) {

    if (stave == null) {
      return null;
    }
    return stave.getBeat();
  }

  @Override
  public AbstractStave<?> set(AbstractStave<?> stave, Beat beat) {

    if (stave != null) {
      stave = stave.setBeat(beat);
    }
    return stave;
  }
}
