package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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
