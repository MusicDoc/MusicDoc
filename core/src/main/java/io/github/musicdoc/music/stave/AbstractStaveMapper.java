package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link AbstractMapper Mapper} for {@link AbstractStave}.
 *
 * @param <T> type of the {@link AbstractStave} to map.
 */
public abstract class AbstractStaveMapper<T extends AbstractStave<T>> extends AbstractMapper<T> {

  /**
   * @param clef the default {@link AbstractStave#getClef() clef}.
   * @param key the default {@link AbstractStave#getKey() key}.
   * @param beat the default {@link AbstractStave#getBeat() beat}.
   * @return the new {@link AbstractStave} instance.
   */
  protected abstract T createStave(Clef clef, MusicalKey key, Beat beat);

}
