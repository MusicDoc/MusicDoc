package io.github.musicdoc.tone;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link TonePitchMapper} for {@link SongFormatMusicDoc}.
 */
public class TonePitchMapperMusicDoc extends TonePitchMapper {

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
