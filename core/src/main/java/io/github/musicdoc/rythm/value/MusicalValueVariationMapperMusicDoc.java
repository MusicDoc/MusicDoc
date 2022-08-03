package io.github.musicdoc.rythm.value;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link MusicalValueVariationMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalValueVariationMapperMusicDoc extends MusicalValueVariationMapper {

  /** The singleton instance. */
  public static final MusicalValueVariationMapperMusicDoc INSTANCE = new MusicalValueVariationMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalValueVariationMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
