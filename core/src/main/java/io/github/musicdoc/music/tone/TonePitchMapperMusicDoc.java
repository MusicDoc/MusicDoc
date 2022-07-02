package io.github.musicdoc.music.tone;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
