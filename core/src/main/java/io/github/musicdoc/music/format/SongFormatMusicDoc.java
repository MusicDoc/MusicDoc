package io.github.musicdoc.music.format;

import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.music.bar.BarLineTypeMapper;
import io.github.musicdoc.music.bar.BarLineTypeMapperMusicDoc;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.clef.ClefMapperMusicDoc;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapperMusicDoc;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapperMusicDoc;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperMusicDoc;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapperMusicDoc;
import io.github.musicdoc.music.harmony.chord.ChordMapper;
import io.github.musicdoc.music.harmony.chord.ChordMapperMusicDoc;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapperMusicDoc;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.music.instrument.InstrumentMapperMusicDoc;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.note.NoteMapperMusicDoc;
import io.github.musicdoc.music.note.StemDirectionMapper;
import io.github.musicdoc.music.note.StemDirectionMapperMusicDoc;
import io.github.musicdoc.music.rythm.beat.BeatMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapperMusicDoc;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.music.rythm.rest.RestMapperMusicDoc;
import io.github.musicdoc.music.rythm.tempo.TempoMapper;
import io.github.musicdoc.music.rythm.tempo.TempoMapperMusicDoc;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueMapperMusicDoc;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapperMusicDoc;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.rythm.value.ValuedItemMapperMusicDoc;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.score.ScoreMapperMusicDoc;
import io.github.musicdoc.music.score.cell.ScoreCellMapper;
import io.github.musicdoc.music.score.cell.ScoreCellMapperMusicDoc;
import io.github.musicdoc.music.score.line.ScoreLineMapper;
import io.github.musicdoc.music.score.line.ScoreLineMapperMusicDoc;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapperMusicDoc;
import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.song.SongMapperMusicDoc;
import io.github.musicdoc.music.stave.StaveChangeMapper;
import io.github.musicdoc.music.stave.StaveChangeMapperMusicDoc;
import io.github.musicdoc.music.stave.StaveMapper;
import io.github.musicdoc.music.stave.StaveMapperMusicDoc;
import io.github.musicdoc.music.stave.system.StaveSystemMapper;
import io.github.musicdoc.music.stave.system.StaveSystemMapperMusicDoc;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapperMusicDoc;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.music.tone.ToneMapperMusicDoc;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperMusicDoc;
import io.github.musicdoc.music.volta.VoltaMapper;
import io.github.musicdoc.music.volta.VoltaMapperMusicDoc;

/**
 * {@link SongFormat} for our default format <a href="https://musicdoc.github.io/">MusicDoc</a>.
 */
public class SongFormatMusicDoc extends SongFormatText {

  /** The singleton instance. */
  public static final SongFormatMusicDoc INSTANCE = new SongFormatMusicDoc();

  private SongFormatMusicDoc() {

    super();
  }

  @Override
  public String getName() {

    return "MusicDoc";
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperMusicDoc.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ToneMapper getToneMapper() {

    return ToneMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ChordMapper getChordMapper() {

    return ChordMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ChordExtensionMapper getChordExtensionMapper() {

    return ChordExtensionMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ChordContainerMapper getChordContainerMapper() {

    return ChordContainerMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MusicalKeyMapper getKeyMapper() {

    return MusicalKeyMapperMusicDoc.INSTANCE;
  }

  @Override
  protected BeatMapper getBeatMapper() {

    return BeatMapperMusicDoc.INSTANCE;
  }

  @Override
  protected TempoMapper getTempoMapper() {

    return TempoMapperMusicDoc.INSTANCE;
  }

  @Override
  protected BarLineMapper getBarLineMapper() {

    return BarLineMapperMusicDoc.INSTANCE;
  }

  @Override
  protected BarLineTypeMapper getBarLineTypeMapper() {

    return BarLineTypeMapperMusicDoc.INSTANCE;
  }

  @Override
  protected VoltaMapper getVoltaMapper() {

    return VoltaMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StaveMapper getStaveMapper() {

    return StaveMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StaveChangeMapper getStaveChangeMapper() {

    return StaveChangeMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StaveSystemMapper getStaveGroupMapper() {

    return StaveSystemMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ValuedItemMapper getValuedItemMapper() {

    return ValuedItemMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MusicalValueMapper getValueMapper() {

    return MusicalValueMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MusicalValueVariationMapper getVariationMapper() {

    return MusicalValueVariationMapperMusicDoc.INSTANCE;
  }

  @Override
  protected RestMapper getRestMapper() {

    return RestMapperMusicDoc.INSTANCE;
  }

  @Override
  protected NoteMapper getNoteMapper() {

    return NoteMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MusicalDecorationMapper getDecorationMapper() {

    return MusicalDecorationMapperMusicDoc.INSTANCE;
  }

  @Override
  protected InstrumentMapper getInstrumentMapper() {

    return InstrumentMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StemDirectionMapper getStemDirectionMapper() {

    return StemDirectionMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ClefMapper getClefMapper() {

    return ClefMapperMusicDoc.INSTANCE;
  }

  @Override
  protected StaveVoiceMapper getStaveVoiceMapper() {

    return StaveVoiceMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return ScoreSectionNameMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreLineMapper getScoreLineMapper() {

    return ScoreLineMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreCellMapper getScoreCellMapper() {

    return ScoreCellMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ScoreMapper getScoreMapper() {

    return ScoreMapperMusicDoc.INSTANCE;
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperMusicDoc.INSTANCE;
  }

}
