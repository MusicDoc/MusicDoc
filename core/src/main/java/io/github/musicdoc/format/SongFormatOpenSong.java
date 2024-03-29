package io.github.musicdoc.format;

import io.github.musicdoc.bar.BarLineMapper;
import io.github.musicdoc.bar.BarLineMapperOpenSong;
import io.github.musicdoc.bar.BarLineTypeMapper;
import io.github.musicdoc.bar.BarLineTypeMapperOpenSong;
import io.github.musicdoc.clef.ClefMapper;
import io.github.musicdoc.decoration.MusicalDecorationMapper;
import io.github.musicdoc.harmony.TonalSystemMapper;
import io.github.musicdoc.harmony.TonalSystemMapperOpenSong;
import io.github.musicdoc.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.harmony.chord.ChordContainerMapperOpenSong;
import io.github.musicdoc.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.harmony.chord.ChordExtensionMapperOpenSong;
import io.github.musicdoc.harmony.chord.ChordSymbolMapper;
import io.github.musicdoc.harmony.chord.ChordSymbolMapperOpenSong;
import io.github.musicdoc.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.harmony.key.MusicalKeyMapperOpenSong;
import io.github.musicdoc.instrument.InstrumentMapper;
import io.github.musicdoc.note.NoteMapper;
import io.github.musicdoc.note.StemDirectionMapper;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapper;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapperOpenSong;
import io.github.musicdoc.rhythm.item.ValuedItemMapper;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.rhythm.metre.MetreMapperOpenSong;
import io.github.musicdoc.rhythm.punctuation.PunctuationMapper;
import io.github.musicdoc.rhythm.rest.RestMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapperOpenSong;
import io.github.musicdoc.rhythm.tuplet.TupletMapper;
import io.github.musicdoc.rhythm.value.MusicalValueMapper;
import io.github.musicdoc.score.ScoreMapper;
import io.github.musicdoc.score.ScoreMapperOpenSong;
import io.github.musicdoc.score.line.ScoreLineBreakMapper;
import io.github.musicdoc.score.line.ScoreLineBreakMapperOpenSong;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.line.ScoreLineMapperOpenSong;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.score.section.ScoreSectionNameMapperOpenSong;
import io.github.musicdoc.song.SongMapper;
import io.github.musicdoc.song.SongMapperOpenSong;
import io.github.musicdoc.stave.StaveChangeMapper;
import io.github.musicdoc.stave.StaveMapper;
import io.github.musicdoc.stave.system.StaveSystemMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.tone.ToneMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapperOpenSong;
import io.github.musicdoc.volta.VoltaMapper;
import io.github.musicdoc.volta.VoltaMapperOpenSong;

/**
 * {@link SongFormat} for OpenSoung.
 */
public class SongFormatOpenSong extends SongFormatXml {

  /** The singleton instance. */
  public static final SongFormatOpenSong INSTANCE = new SongFormatOpenSong();

  private SongFormatOpenSong() {

    super();
  }

  @Override
  public String getName() {

    return "OpenSong";
  }

  @Override
  protected String getRootTag() {

    return "song";
  }

  @Override
  protected boolean isSupportItem() {

    return false;
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperOpenSong.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperOpenSong.INSTANCE;
  }

  @Override
  protected ToneMapper getToneMapper() {

    return null;
  }

  @Override
  protected ChordSymbolMapper getChordSymbolMapper() {

    return ChordSymbolMapperOpenSong.INSTANCE;
  }

  @Override
  protected ChordExtensionMapper getChordExtensionMapper() {

    return ChordExtensionMapperOpenSong.INSTANCE;
  }

  @Override
  protected ChordContainerMapper getChordContainerMapper() {

    return ChordContainerMapperOpenSong.INSTANCE;
  }

  @Override
  protected MusicalKeyMapper getKeyMapper() {

    return MusicalKeyMapperOpenSong.INSTANCE;
  }

  @Override
  protected PlainFractionMapper getPlainFractionMapper() {

    return PlainFractionMapperOpenSong.INSTANCE;
  }

  @Override
  protected MetreMapper getBeatMapper() {

    return MetreMapperOpenSong.INSTANCE;
  }

  @Override
  protected TempoMapper getTempoMapper() {

    return TempoMapperOpenSong.INSTANCE;
  }

  @Override
  protected BarLineMapper getBarLineMapper() {

    return BarLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected BarLineTypeMapper getBarLineTypeMapper() {

    return BarLineTypeMapperOpenSong.INSTANCE;
  }

  @Override
  protected VoltaMapper getVoltaMapper() {

    return VoltaMapperOpenSong.INSTANCE;
  }

  @Override
  protected StaveMapper getStaveMapper() {

    return null;
  }

  @Override
  protected StaveChangeMapper getStaveChangeMapper() {

    return null;
  }

  @Override
  protected StaveSystemMapper getStaveGroupMapper() {

    return null;
  }

  @Override
  protected ValuedItemMapper getValuedItemMapper() {

    return null;
  }

  @Override
  protected MusicalValueMapper getValueMapper() {

    return null;
  }

  @Override
  protected PunctuationMapper getPunctuationMapper() {

    return null;
  }

  @Override
  protected TupletMapper getTupletMapper() {

    return null;
  }

  @Override
  protected RestMapper getRestMapper() {

    return null;
  }

  @Override
  protected NoteMapper getNoteMapper() {

    return null;
  }

  @Override
  protected MusicalDecorationMapper getDecorationMapper() {

    return null;
  }

  @Override
  protected InstrumentMapper getInstrumentMapper() {

    return null;
  }

  @Override
  protected StemDirectionMapper getStemDirectionMapper() {

    return null;
  }

  @Override
  protected ClefMapper getClefMapper() {

    return null;
  }

  @Override
  protected StaveVoiceMapper getStaveVoiceMapper() {

    return null;
  }

  @Override
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return ScoreSectionNameMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreLineMapper getScoreLineMapper() {

    return ScoreLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreLineBreakMapper getScoreLineBreakMapper() {

    return ScoreLineBreakMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreMapper getScoreMapper() {

    return ScoreMapperOpenSong.INSTANCE;
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperOpenSong.INSTANCE;
  }

}
