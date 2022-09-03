package io.github.musicdoc.format;

import io.github.musicdoc.bar.BarLineMapper;
import io.github.musicdoc.bar.BarLineMapperAbc;
import io.github.musicdoc.bar.BarLineTypeMapper;
import io.github.musicdoc.bar.BarLineTypeMapperAbc;
import io.github.musicdoc.clef.ClefMapper;
import io.github.musicdoc.clef.ClefMapperAbc;
import io.github.musicdoc.decoration.MusicalDecorationMapper;
import io.github.musicdoc.decoration.MusicalDecorationMapperAbc;
import io.github.musicdoc.harmony.TonalSystemMapper;
import io.github.musicdoc.harmony.TonalSystemMapperAbc;
import io.github.musicdoc.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.harmony.chord.ChordContainerMapperAbc;
import io.github.musicdoc.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.harmony.chord.ChordExtensionMapperAbc;
import io.github.musicdoc.harmony.chord.ChordMapper;
import io.github.musicdoc.harmony.chord.ChordMapperAbc;
import io.github.musicdoc.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.harmony.key.MusicalKeyMapperAbc;
import io.github.musicdoc.instrument.InstrumentMapper;
import io.github.musicdoc.instrument.InstrumentMapperAbc;
import io.github.musicdoc.io.PropertyState;
import io.github.musicdoc.note.NoteMapper;
import io.github.musicdoc.note.NoteMapperAbc;
import io.github.musicdoc.note.StemDirectionMapper;
import io.github.musicdoc.note.StemDirectionMapperAbc;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapper;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapperAbc;
import io.github.musicdoc.rhythm.item.ValuedItemMapper;
import io.github.musicdoc.rhythm.item.ValuedItemMapperAbc;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.rhythm.metre.MetreMapperAbc;
import io.github.musicdoc.rhythm.rest.RestMapper;
import io.github.musicdoc.rhythm.rest.RestMapperAbc;
import io.github.musicdoc.rhythm.tempo.TempoMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapperAbc;
import io.github.musicdoc.rhythm.value.MusicalValueMapper;
import io.github.musicdoc.rhythm.value.MusicalValueMapperAbc;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariationMapper;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariationMapperAbc;
import io.github.musicdoc.score.ScoreMapper;
import io.github.musicdoc.score.ScoreMapperAbc;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.line.ScoreLineMapperAbc;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.score.section.ScoreSectionNameMapperAbc;
import io.github.musicdoc.song.SongMapper;
import io.github.musicdoc.song.SongMapperAbc;
import io.github.musicdoc.stave.StaveChangeMapper;
import io.github.musicdoc.stave.StaveChangeMapperAbc;
import io.github.musicdoc.stave.StaveMapper;
import io.github.musicdoc.stave.StaveMapperAbc;
import io.github.musicdoc.stave.system.StaveSystemMapper;
import io.github.musicdoc.stave.system.StaveSystemMapperAbc;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapperAbc;
import io.github.musicdoc.tone.ToneMapper;
import io.github.musicdoc.tone.ToneMapperAbc;
import io.github.musicdoc.tone.pitch.TonePitchMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapperAbc;
import io.github.musicdoc.volta.VoltaMapper;
import io.github.musicdoc.volta.VoltaMapperAbc;

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
  PlainFraction getUnitNoteLength(Metre beat) {

    PlainFraction unitNoteLength = PlainFraction._1_8;
    if (beat != null) {
      double value = beat.getValue();
      if (value < 0.75) {
        unitNoteLength = PlainFraction._1_16;
      }
    }
    return unitNoteLength;
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
  protected PlainFractionMapper getPlainFractionMapper() {

    return PlainFractionMapperAbc.INSTANCE;
  }

  @Override
  protected MetreMapper getBeatMapper() {

    return MetreMapperAbc.INSTANCE;
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

    return ScoreSectionNameMapperAbc.INSTANCE;
  }

  @Override
  protected ScoreLineMapper getScoreLineMapper() {

    return ScoreLineMapperAbc.INSTANCE;
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
