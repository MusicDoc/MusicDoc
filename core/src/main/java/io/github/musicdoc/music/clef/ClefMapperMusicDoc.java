package io.github.musicdoc.music.clef;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ClefMapper} for {@link SongFormatMusicDoc}.
 */
public class ClefMapperMusicDoc extends ClefMapper {

  /** The singleton instance. */
  public static final ClefMapperMusicDoc INSTANCE = new ClefMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ClefMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
