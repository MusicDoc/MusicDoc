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
  public Beat get(Stave stave) {

    if (stave == null) {
      return null;
    }
    return stave.getBeat();
  }

  @Override
  public void set(Stave stave, Beat beat) {

    if (stave != null) {
      stave.setBeat(beat);
    }
  }
}
