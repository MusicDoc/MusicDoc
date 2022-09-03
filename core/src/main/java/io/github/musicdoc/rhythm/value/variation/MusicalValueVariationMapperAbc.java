package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link MusicalValueVariationMapper} for {@link SongFormatAbc}.
 */
public class MusicalValueVariationMapperAbc extends MusicalValueVariationMapperBase {

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

  @Override
  protected boolean isSupportPunctuation() {

    return false;
  }

}
