package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.rythm.Fraction;
import io.github.musicdoc.music.rythm.beat.BeatMapperMusicDoc;

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

  @Override
  protected AbstractMapper<? extends Fraction> getFractionMapper() {

    return BeatMapperMusicDoc.INSTANCE;
  }

}
