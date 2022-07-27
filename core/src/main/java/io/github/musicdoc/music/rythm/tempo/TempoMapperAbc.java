package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link TempoMapper} for {@link SongFormatAbc}.
 */
public class TempoMapperAbc extends TempoMapper {

  /** The singleton instance. */
  public static final TempoMapperAbc INSTANCE = new TempoMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
