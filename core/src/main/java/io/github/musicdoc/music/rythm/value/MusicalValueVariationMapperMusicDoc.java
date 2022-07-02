package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
