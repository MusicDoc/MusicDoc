package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link MusicalValueMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalValueMapperMusicDoc extends MusicalValueMapper {

  /** The singleton instance. */
  public static final MusicalValueMapperMusicDoc INSTANCE = new MusicalValueMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalValueMapperMusicDoc() {

    super();
  }

  @Override
  protected MusicalValueVariationMapper getVariationMapper() {

    return MusicalValueVariationMapperMusicDoc.INSTANCE;
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
