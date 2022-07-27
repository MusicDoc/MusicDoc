package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.clef.Clef;

/**
 * {@link StaveProperty} for {@link Stave#getClef() clef property}.
 */
class StavePropertyClef extends StaveProperty<Clef> {

  public static final StavePropertyClef INSTANCE = new StavePropertyClef();

  public StavePropertyClef() {

    super("clef");
  }

  @Override
  public Clef get(AbstractStave<?> stave) {

    if (stave == null) {
      return null;
    }
    return stave.getClef();
  }

  @Override
  public AbstractStave<?> set(AbstractStave<?> stave, Clef clef) {

    if (stave != null) {
      stave = stave.setClef(clef);
    }
    return stave;
  }
}
