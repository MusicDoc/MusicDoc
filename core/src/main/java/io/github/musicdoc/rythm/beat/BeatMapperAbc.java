package io.github.musicdoc.rythm.beat;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link BeatMapper} for {@link SongFormatAbc}.
 */
public class BeatMapperAbc extends BeatMapper {

  /** The singleton instance. */
  public static final BeatMapperAbc INSTANCE = new BeatMapperAbc();

  /**
   * The constructor.
   */
  protected BeatMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
