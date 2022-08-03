package io.github.musicdoc.stave;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}.
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
