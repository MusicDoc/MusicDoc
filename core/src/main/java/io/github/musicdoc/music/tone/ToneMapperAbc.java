package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperAbc extends ToneMapper {

  /** The singleton instance. */
  public static final ToneMapperAbc INSTANCE = new ToneMapperAbc();

  /**
   * The constructor.
   */
  protected ToneMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
