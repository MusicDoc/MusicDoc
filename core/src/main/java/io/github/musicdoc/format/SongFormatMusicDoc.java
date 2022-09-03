package io.github.musicdoc.format;

import io.github.musicdoc.bar.BarLineMapper;
import io.github.musicdoc.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.bar.BarLineTypeMapper;
import io.github.musicdoc.bar.BarLineTypeMapperMusicDoc;
import io.github.musicdoc.clef.ClefMapper;
import io.github.musicdoc.clef.ClefMapperMusicDoc;
import io.github.musicdoc.decoration.MusicalDecorationMapper;
import io.github.musicdoc.decoration.MusicalDecorationMapperMusicDoc;
import io.github.musicdoc.harmony.TonalSystemMapper;
import io.github.musicdoc.harmony.TonalSystemMapperMusicDoc;
import io.github.musicdoc.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.harmony.chord.ChordContainerMapperMusicDoc;
import io.github.musicdoc.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.harmony.chord.ChordExtensionMapperMusicDoc;
import io.github.musicdoc.harmony.chord.ChordMapper;
import io.github.musicdoc.harmony.chord.ChordMapperMusicDoc;
import io.github.musicdoc.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.harmony.key.MusicalKeyMapperMusicDoc;
import io.github.musicdoc.instrument.InstrumentMapper;
import io.github.musicdoc.instrument.InstrumentMapperMusicDoc;
import io.github.musicdoc.note.NoteMapper;
import io.github.musicdoc.note.NoteMapperMusicDoc;
import io.github.musicdoc.note.StemDirectionMapper;
import io.github.musicdoc.note.StemDirectionMapperMusicDoc;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapper;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapperMusicDoc;
import io.github.musicdoc.rhythm.item.ValuedItemMapper;
import io.github.musicdoc.rhythm.item.ValuedItemMapperMusicDoc;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.rhythm.metre.MetreMapperMusicDoc;
import io.github.musicdoc.rhythm.rest.RestMapper;
import io.github.musicdoc.rhythm.rest.RestMapperMusicDoc;
import io.github.musicdoc.rhythm.tempo.TempoMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapperMusicDoc;
import io.github.musicdoc.rhythm.value.MusicalValueMapper;
import io.github.musicdoc.rhythm.value.MusicalValueMapperMusicDoc;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariationMapper;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariationMapperMusicDoc;
import io.github.musicdoc.score.ScoreMapper;
import io.github.musicdoc.score.ScoreMapperMusicDoc;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.line.ScoreLineMapperMusicDoc;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.score.section.ScoreSectionNameMapperMusicDoc;
import io.github.musicdoc.song.SongMapper;
import io.github.musicdoc.song.SongMapperMusicDoc;
import io.github.musicdoc.stave.StaveChangeMapper;
import io.github.musicdoc.stave.StaveChangeMapperMusicDoc;
import io.github.musicdoc.stave.StaveMapper;
import io.github.musicdoc.stave.StaveMapperMusicDoc;
import io.github.musicdoc.stave.system.StaveSystemMapper;
import io.github.musicdoc.stave.system.StaveSystemMapperMusicDoc;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapperMusicDoc;
import io.github.musicdoc.tone.ToneMapper;
import io.github.musicdoc.tone.ToneMapperMusicDoc;
import io.github.musicdoc.tone.pitch.TonePitchMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapperMusicDoc;
import io.github.musicdoc.volta.VoltaMapper;
import io.github.musicdoc.volta.VoltaMapperMusicDoc;

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
  protected PlainFractionMapper getPlainFractionMapper() {

    return PlainFractionMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MetreMapper getBeatMapper() {

    return MetreMapperMusicDoc.INSTANCE;
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
  protected ScoreMapper getScoreMapper() {

    return ScoreMapperMusicDoc.INSTANCE;
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperMusicDoc.INSTANCE;
  }

}
