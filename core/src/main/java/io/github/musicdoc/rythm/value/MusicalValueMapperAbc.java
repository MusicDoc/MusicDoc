package io.github.musicdoc.rythm.value;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link MusicalValueMapper} for {@link SongFormatAbc}.
 */
public class MusicalValueMapperAbc extends MusicalValueMapper {

  /** The singleton instance. */
  public static final MusicalValueMapperAbc INSTANCE = new MusicalValueMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalValueMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected boolean isSupportVariation() {

    return false;
  }

}
