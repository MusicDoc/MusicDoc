package io.github.musicdoc.rhythm.tempo;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link TempoMapper} for {@link SongFormatMusicDoc}.
 */
public class TempoMapperMusicDoc extends TempoMapper {

  /** The singleton instance. */
  public static final TempoMapperMusicDoc INSTANCE = new TempoMapperMusicDoc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
