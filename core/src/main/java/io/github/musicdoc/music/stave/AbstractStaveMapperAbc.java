package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
 *
 * @param <T> type of the {@link AbstractStave} to map.
 */
public abstract class AbstractStaveMapperAbc<T extends AbstractStave<T>> extends AbstractStaveMapperProperties<T> {

  /**
   * The constructor.
   */
  protected AbstractStaveMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
