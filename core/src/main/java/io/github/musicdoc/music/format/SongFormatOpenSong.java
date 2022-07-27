package io.github.musicdoc.music.format;

import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperOpenSong;
import io.github.musicdoc.music.bar.BarLineTypeMapper;
import io.github.musicdoc.music.bar.BarLineTypeMapperOpenSong;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapperOpenSong;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperOpenSong;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapperOpenSong;
import io.github.musicdoc.music.harmony.chord.ChordMapper;
import io.github.musicdoc.music.harmony.chord.ChordMapperOpenSong;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapperOpenSong;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.note.StemDirectionMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapperOpenSong;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.music.rythm.tempo.TempoMapper;
import io.github.musicdoc.music.rythm.tempo.TempoMapperOpenSong;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapper;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.score.ScoreLineMapper;
import io.github.musicdoc.music.score.ScoreLineMapperOpenSong;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.score.ScoreMapperOpenSong;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapperOpenSong;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapperOpenSong;
import io.github.musicdoc.music.score.voice.ScoreVoiceCellMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapperOpenSong;
import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.song.SongMapperOpenSong;
import io.github.musicdoc.music.stave.StaveChangeMapper;
import io.github.musicdoc.music.stave.StaveMapper;
import io.github.musicdoc.music.stave.system.StaveSystemMapper;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperOpenSong;
import io.github.musicdoc.music.volta.VoltaMapper;
import io.github.musicdoc.music.volta.VoltaMapperOpenSong;

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
  protected ChordMapper getChordMapper() {

    return ChordMapperOpenSong.INSTANCE;
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
  protected BeatMapper getBeatMapper() {

    return BeatMapperOpenSong.INSTANCE;
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
  protected MusicalValueVariationMapper getVariationMapper() {

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
  protected ScoreCommentLineMapper getCommentLineMapper() {

    return ScoreCommentLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreVoiceLineMapper getVoiceLineMapper() {

    return ScoreVoiceLineMapperOpenSong.INSTANCE;
  }

  @Override
  protected ScoreVoiceCellMapper getVoiceCellMapper() {

    return null;
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
