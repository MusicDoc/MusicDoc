package io.github.musicdoc.tone.pitch;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link TonePitchMapper} for {@link SongFormatAbc}.
 */
public class TonePitchMapperAbc extends TonePitchMapperBase {

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
