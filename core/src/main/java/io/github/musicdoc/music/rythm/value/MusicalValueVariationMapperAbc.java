package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link MusicalValueVariationMapper} for {@link SongFormatAbc}.
 */
public class MusicalValueVariationMapperAbc extends MusicalValueVariationMapper {

  /** The singleton instance. */
  public static final MusicalValueVariationMapperAbc INSTANCE = new MusicalValueVariationMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalValueVariationMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
