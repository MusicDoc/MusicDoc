package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ToneMapper} for {@link SongFormatMusicDoc}.
 */
public class ToneMapperMusicDoc extends ToneMapper {

  /** The singleton instance. */
  public static final ToneMapperMusicDoc INSTANCE = new ToneMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ToneMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperMusicDoc.INSTANCE;
  }

}
