package io.github.musicdoc.music.decoration;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link MusicalDecorationMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalDecorationMapperMusicDoc extends MusicalDecorationMapper {

  /** The singleton instance. */
  public static final MusicalDecorationMapperMusicDoc INSTANCE = new MusicalDecorationMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalDecorationMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
