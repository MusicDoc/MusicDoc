package io.github.musicdoc.tone.pitch;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link TonePitchMapper} for {@link SongFormatMusicDoc}.
 */
public class TonePitchMapperMusicDoc extends TonePitchMapperBase {

  /** The singleton instance. */
  public static final TonePitchMapperMusicDoc INSTANCE = new TonePitchMapperMusicDoc();

  /**
   * The constructor.
   */
  protected TonePitchMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
