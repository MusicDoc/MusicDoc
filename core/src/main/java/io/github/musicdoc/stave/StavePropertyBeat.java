package io.github.musicdoc.stave;

import io.github.musicdoc.rhythm.metre.Metre;

/**
 * {@link StaveProperty} for the {@link Stave#getMetre() beat property}.
 */
class StavePropertyBeat extends StaveProperty<Metre> {

  public static final StavePropertyBeat INSTANCE = new StavePropertyBeat();

  public StavePropertyBeat() {

    super("beat");
  }

  @Override
  public Metre get(AbstractStave<?> stave) {

    if (stave == null) {
      return null;
    }
    return stave.getMetre();
  }

  @Override
  public AbstractStave<?> set(AbstractStave<?> stave, Metre beat) {

    if (stave != null) {
      stave = stave.setMetre(beat);
    }
    return stave;
  }
}
