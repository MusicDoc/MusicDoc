package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link MusicalValueVariationMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalValueVariationMapperMusicDoc extends MusicalValueVariationMapperBase {

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
