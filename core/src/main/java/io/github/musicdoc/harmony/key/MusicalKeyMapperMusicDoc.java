package io.github.musicdoc.harmony.key;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link MusicalKeyMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalKeyMapperMusicDoc extends MusicalKeyMapper {

  /** The singleton instance. */
  public static final MusicalKeyMapperMusicDoc INSTANCE = new MusicalKeyMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalKeyMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
