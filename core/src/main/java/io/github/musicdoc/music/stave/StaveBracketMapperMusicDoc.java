package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link StaveBracketMapper} for {@link SongFormatMusicDoc}.
 */
public class StaveBracketMapperMusicDoc extends StaveBracketMapper {

  /** The singleton instance. */
  public static final StaveBracketMapperMusicDoc INSTANCE = new StaveBracketMapperMusicDoc();

  /**
   * The constructor.
   */
  protected StaveBracketMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
