package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link TonePitchMapper} for {@link SongFormatAbc}.
 */
public class TonePitchMapperAbc extends TonePitchMapper {

  /** The singleton instance. */
  public static final TonePitchMapperAbc INSTANCE = new TonePitchMapperAbc();

  /**
   * The constructor.
   */
  protected TonePitchMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
