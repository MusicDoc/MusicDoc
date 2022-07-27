package io.github.musicdoc.music.format;

import io.github.musicdoc.io.PropertyState;
import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperAbc;
import io.github.musicdoc.music.bar.BarLineTypeMapper;
import io.github.musicdoc.music.bar.BarLineTypeMapperAbc;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.clef.ClefMapperAbc;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapperAbc;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapperAbc;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperAbc;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapperAbc;
import io.github.musicdoc.music.harmony.chord.ChordMapper;
import io.github.musicdoc.music.harmony.chord.ChordMapperAbc;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapperAbc;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.music.instrument.InstrumentMapperAbc;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.note.NoteMapperAbc;
import io.github.musicdoc.music.note.StemDirectionMapper;
import io.github.musicdoc.music.note.StemDirectionMapperAbc;
import io.github.musicdoc.music.rythm.beat.BeatMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapperAbc;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.music.rythm.rest.RestMapperAbc;
import io.github.musicdoc.music.rythm.tempo.TempoMapper;
import io.github.musicdoc.music.rythm.tempo.TempoMapperAbc;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueMapperAbc;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapperAbc;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.rythm.value.ValuedItemMapperAbc;
import io.github.musicdoc.music.score.ScoreLineMapper;
import io.github.musicdoc.music.score.ScoreLineMapperAbc;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.score.ScoreMapperAbc;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapperAbc;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceCellMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceCellMapperAbc;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapperAbc;
import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.song.SongMapperAbc;
import io.github.musicdoc.music.stave.StaveChangeMapper;
import io.github.musicdoc.music.stave.StaveChangeMapperAbc;
import io.github.musicdoc.music.stave.StaveMapper;
import io.github.musicdoc.music.stave.StaveMapperAbc;
import io.github.musicdoc.music.stave.system.StaveSystemMapper;
import io.github.musicdoc.music.stave.system.StaveSystemMapperAbc;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapperAbc;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.music.tone.ToneMapperAbc;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperAbc;
import io.github.musicdoc.music.volta.VoltaMapper;
import io.github.musicdoc.music.volta.VoltaMapperAbc;

/**
 * {@link SongFormat} for our default format <a href="https://musicdoc.github.io/">MusicDoc</a>.
 */
public class SongFormatAbc extends SongFormatText {

  /** The singleton instance. */
  public static final SongFormatAbc INSTANCE = new SongFormatAbc();

  private SongFormatAbc() {

    super();
  }

  @Override
  public String getName() {

    return "abc";
  }

  @Override
  protected PropertyState newPropertyState() {

    return PropertyState.of("+:");
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperAbc.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperAbc.INSTANCE;
  }

  @Override
  protected ToneMapper getToneMapper() {

    return ToneMapperAbc.INSTANCE;
  }

  @Override
  protected ValuedItemMapper getValuedItemMapper() {

    return ValuedItemMapperAbc.INSTANCE;
  }

  @Override
  protected MusicalValueMapper getValueMapper() {

    return MusicalValueMapperAbc.INSTANCE;
  }

  @Override
  protected MusicalValueVariationMapper getVariationMapper() {

    return MusicalValueVariationMapperAbc.INSTANCE;
  }

  @Override
  protected RestMapper getRestMapper() {

    return RestMapperAbc.INSTANCE;
  }

  @Override
  protected NoteMapper getNoteMapper() {

    return NoteMapperAbc.INSTANCE;
  }

  @Override
  protected MusicalDecorationMapper getDecorationMapper() {

    return MusicalDecorationMapperAbc.INSTANCE;
  }

  @Override
  protected ChordMapper getChordMapper() {

    return ChordMapperAbc.INSTANCE;
  }

  @Override
  protected ChordExtensionMapper getChordExtensionMapper() {

    return ChordExtensionMapperAbc.INSTANCE;
  }

  @Override
  protected ChordContainerMapper getChordContainerMapper() {

    return ChordContainerMapperAbc.INSTANCE;
  }

  @Override
  protected MusicalKeyMapper getKeyMapper() {

    return MusicalKeyMapperAbc.INSTANCE;
  }

  @Override
  protected BeatMapper getBeatMapper() {

    return BeatMapperAbc.INSTANCE;
  }

  @Override
  protected TempoMapper getTempoMapper() {

    return TempoMapperAbc.INSTANCE;
  }

  @Override
  protected BarLineMapper getBarLineMapper() {

    return BarLineMapperAbc.INSTANCE;
  }

  @Override
  protected BarLineTypeMapper getBarLineTypeMapper() {

    return BarLineTypeMapperAbc.INSTANCE;
  }

  @Override
  protected VoltaMapper getVoltaMapper() {

    return VoltaMapperAbc.INSTANCE;
  }

  @Override
  protected InstrumentMapper getInstrumentMapper() {

    return InstrumentMapperAbc.INSTANCE;
  }

  @Override
  protected StemDirectionMapper getStemDirectionMapper() {

    return StemDirectionMapperAbc.INSTANCE;
  }

  @Override
  protected ClefMapper getClefMapper() {

    return ClefMapperAbc.INSTANCE;
  }

  @Override
  protected StaveMapper getStaveMapper() {

    return StaveMapperAbc.INSTANCE;
  }

  @Override
  protected StaveChangeMapper getStaveChangeMapper() {

    return StaveChangeMapperAbc.INSTANCE;
  }

  @Override
  protected StaveVoiceMapper getStaveVoiceMapper() {

    return StaveVoiceMapperAbc.INSTANCE;
  }

  @Override
  protected StaveSystemMapper getStaveGroupMapper() {

    return StaveSystemMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return null;
  }

  @Override
  protected ScoreLineMapper getScoreLineMapper() {

    return ScoreLineMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreCommentLineMapper getCommentLineMapper() {

    return ScoreCommentLineMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreVoiceLineMapper getVoiceLineMapper() {

    return ScoreVoiceLineMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreVoiceCellMapper getVoiceCellMapper() {

    return ScoreVoiceCellMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreMapper getScoreMapper() {

    return ScoreMapperAbc.INSTANCE;
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperAbc.INSTANCE;
  }

}
