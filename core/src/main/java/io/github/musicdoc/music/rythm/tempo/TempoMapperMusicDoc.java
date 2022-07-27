package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
