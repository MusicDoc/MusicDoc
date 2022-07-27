package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.harmony.key.MusicalKey;

/**
 * {@link StaveProperty} to {@link Stave#getKey() key property}.
 */
class StavePropertyKey extends StaveProperty<MusicalKey> {

  public static final StavePropertyKey INSTANCE = new StavePropertyKey();

  public StavePropertyKey() {

    super("key");
  }

  @Override
  public MusicalKey get(AbstractStave<?> stave) {

    if (stave == null) {
      return null;
    }
    return stave.getKey();
  }

  @Override
  public AbstractStave<?> set(AbstractStave<?> stave, MusicalKey key) {

    if (stave != null) {
      stave = stave.setKey(key);
    }
    return stave;
  }
}
