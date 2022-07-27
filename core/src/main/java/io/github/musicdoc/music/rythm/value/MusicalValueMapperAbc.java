package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

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

}
