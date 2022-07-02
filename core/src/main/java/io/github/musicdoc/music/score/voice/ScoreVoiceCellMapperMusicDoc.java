package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperMusicDoc;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.rythm.value.ValuedItemMapperMusicDoc;
import io.github.musicdoc.music.stave.StaveMapper;
import io.github.musicdoc.music.stave.StaveMapperMusicDoc;

/**
 * {@link ScoreVoiceCellMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreVoiceCellMapperMusicDoc extends ScoreVoiceCellMapper {

  /** The singleton instance. */
  public static final ScoreVoiceCellMapperMusicDoc INSTANCE = new ScoreVoiceCellMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreVoiceCellMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected BarLineMapper getBarLineMapper() {

    return BarLineMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StaveMapper getStaveMapper() {

    return StaveMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ChordContainerMapper getChordContainerMapper() {

    return ChordContainerMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ValuedItemMapper getValuedItemMapper() {

    return ValuedItemMapperMusicDoc.INSTANCE;
  }
}
