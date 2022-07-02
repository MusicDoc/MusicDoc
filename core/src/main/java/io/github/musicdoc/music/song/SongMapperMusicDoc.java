package io.github.musicdoc.music.song;

import io.github.musicdoc.music.format.IntegerMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.format.StringMapper;
import io.github.musicdoc.music.harmony.MusicalKeyMapperMusicDoc;
import io.github.musicdoc.music.rythm.beat.BeatMapperMusicDoc;
import io.github.musicdoc.music.rythm.tempo.TempoMapperMusicDoc;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.score.ScoreMapperMusicDoc;

/**
 * {@link SongMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
 */
public class SongMapperMusicDoc extends SongMapper {

  /** The singleton instance. */
  public static final SongMapperMusicDoc INSTANCE = new SongMapperMusicDoc();

  /**
   * The constructor.
   */
  protected SongMapperMusicDoc() {

    super(SongPropertyMapper.of(TEMPLATE.beat, PROPERTY_METER, BeatMapperMusicDoc.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.unitNoteLength, PROPERTY_UNIT_NOTE_LENGTH, BeatMapperMusicDoc.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.key, PROPERTY_KEY, MusicalKeyMapperMusicDoc.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.tempo, PROPERTY_TEMPO, TempoMapperMusicDoc.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.title, PROPERTY_TITLE, StringMapper.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.composer, PROPERTY_COMPOSER, StringMapper.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.album, PROPERTY_ALBUM, StringMapper.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.capo, PROPERTY_CAPO, IntegerMapper.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.referenceNumber, PROPERTY_REFERENCE_NUMBER, IntegerMapper.INSTANCE),
        SongPropertyMapper.of(TEMPLATE.origin, PROPERTY_ORIGIN, StringMapper.INSTANCE));
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public ScoreMapper getScoreMapper() {

    return ScoreMapperMusicDoc.INSTANCE;
  }

}
