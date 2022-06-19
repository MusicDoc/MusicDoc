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
  public Clef get(Stave stave) {

    if (stave == null) {
      return null;
    }
    return stave.getClef();
  }

  @Override
  public void set(Stave stave, Clef clef) {

    if (stave != null) {
      stave.setClef(clef);
    }
  }
}
