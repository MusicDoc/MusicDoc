package io.github.musicdoc.rhythm.value;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link MusicalValueMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalValueMapperMusicDoc extends MusicalValueMapperBase {

  /** The singleton instance. */
  public static final MusicalValueMapperMusicDoc INSTANCE = new MusicalValueMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalValueMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
