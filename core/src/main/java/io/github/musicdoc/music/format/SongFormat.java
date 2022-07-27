package io.github.musicdoc.music.format;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.music.bar.BarLineTypeMapper;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.music.harmony.chord.ChordMapper;
import io.github.musicdoc.music.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.note.StemDirectionMapper;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.rythm.beat.BeatMapper;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.music.rythm.tempo.TempoMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueVariationMapper;
import io.github.musicdoc.music.rythm.value.ValuedItemMapper;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreLineMapper;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;
import io.github.musicdoc.music.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceCellMapper;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineMapper;
import io.github.musicdoc.music.song.Song;
import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.stave.StaveChangeMapper;
import io.github.musicdoc.music.stave.StaveMapper;
import io.github.musicdoc.music.stave.system.StaveSystemMapper;
import io.github.musicdoc.music.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.volta.VoltaMapper;

/**
 * Abstract base class for a music format allowing to {@link #write(Song, OutputStream) wirte (format)} and
 * {@link #read(InputStream) read (parse)} a {@link Song} and all its child elements..
 */
public abstract class SongFormat {

  private static final Map<String, SongFormat> FORMAT_MAP = new HashMap<>();

  /**
   * The constructor.
   */
  protected SongFormat() {

    super();
    FORMAT_MAP.put(getName(), this);
  }

  /**
   * @param payload the entire playload (data of the input stream) as {@link String}.
   * @return a {@link MusicInputStream} to read the data from.
   */
  protected abstract MusicInputStream createInputStream(String payload);

  /**
   * @param out the {@link Appendable} to write to.
   * @return a {@link MusicOutputStream} to write the data to.
   */
  protected abstract MusicOutputStream createOutputStream(Appendable out);

  /**
   * @return the underlying {@link ScoreMapper} to parse and format {@link Score}s.
   */
  protected abstract ScoreMapper getScoreMapper();

  /**
   * @return the {@link TonePitchMapper}.
   */
  protected abstract TonePitchMapper getTonePitchMapper();

  /**
   * @return the {@link ToneMapper}.
   */
  protected abstract ToneMapper getToneMapper();

  /**
   * @return the {@link TonalSystemMapper}.
   */
  protected abstract TonalSystemMapper getTonalSystemMapper();

  /**
   * @return the {@link ChordMapper}.
   */
  protected abstract ChordMapper getChordMapper();

  /**
   * @return the {@link ChordExtensionMapper}.
   */
  protected abstract ChordExtensionMapper getChordExtensionMapper();

  /**
   * @return the {@link ChordContainerMapper}.
   */
  protected abstract ChordContainerMapper getChordContainerMapper();

  /**
   * @return the {@link MusicalKeyMapper}.
   */
  protected abstract MusicalKeyMapper getKeyMapper();

  /**
   * @return the {@link BeatMapper}.
   */
  protected abstract BeatMapper getBeatMapper();

  /**
   * @return the {@link TempoMapper}.
   */
  protected abstract TempoMapper getTempoMapper();

  /**
   * @return the {@link BarLineMapperMusicDoc}.
   */
  protected abstract BarLineMapper getBarLineMapper();

  /**
   * @return the {@link BarLineTypeMapper}.
   */
  protected abstract BarLineTypeMapper getBarLineTypeMapper();

  /**
   * @return the {@link VoltaMapper}.
   */
  protected abstract VoltaMapper getVoltaMapper();

  /**
   * @return the {@link StaveMapper}.
   */
  protected abstract StaveMapper getStaveMapper();

  /**
   * @return the {@link StaveChangeMapper}.
   */
  protected abstract StaveChangeMapper getStaveChangeMapper();

  /**
   * @return the {@link StaveSystemMapper}.
   */
  protected abstract StaveSystemMapper getStaveGroupMapper();

  /**
   * @return the {@link MusicalValueMapper}.
   */
  protected abstract MusicalValueMapper getValueMapper();

  /**
   * @return the {@link MusicalValueVariationMapper}.
   */
  protected abstract MusicalValueVariationMapper getVariationMapper();

  /**
   * @return the {@link ValuedItemMapper}.
   */
  protected abstract ValuedItemMapper getValuedItemMapper();

  /**
   * @return the {@link RestMapper}.
   */
  protected abstract RestMapper getRestMapper();

  /**
   * @return the {@link NoteMapper}.
   */
  protected abstract NoteMapper getNoteMapper();

  /**
   * @return the {@link MusicalDecorationMapper}.
   */
  protected abstract MusicalDecorationMapper getDecorationMapper();

  /**
   * @return the {@link InstrumentMapper}.
   */
  protected abstract InstrumentMapper getInstrumentMapper();

  /**
   * @return the {@link StemDirectionMapper}.
   */
  protected abstract StemDirectionMapper getStemDirectionMapper();

  /**
   * @return the {@link ClefMapper}.
   */
  protected abstract ClefMapper getClefMapper();

  /**
   * @return the {@link StaveVoiceMapper}.
   */
  protected abstract StaveVoiceMapper getStaveVoiceMapper();

  /**
   * @return the underlying {@link SongMapper} to parse and format {@link Song}s.
   */
  protected abstract SongMapper getSongMapper();

  /**
   * @return the {@link ScoreSectionNameMapper}.
   */
  protected abstract ScoreSectionNameMapper getSectionNameMapper();

  /**
   * @return the {@link ScoreLineMapper}.
   */
  protected abstract ScoreLineMapper getScoreLineMapper();

  /**
   * @return the {@link ScoreCommentLineMapper}.
   */
  protected abstract ScoreCommentLineMapper getCommentLineMapper();

  /**
   * @return the {@link ScoreVoiceLineMapper}.
   */
  protected abstract ScoreVoiceLineMapper getVoiceLineMapper();

  /**
   * @return the {@link ScoreVoiceCellMapper}
   */
  protected abstract ScoreVoiceCellMapper getVoiceCellMapper();

  /**
   * @return the name of this formatSection.
   */
  public abstract String getName();

  /**
   * @param inStream the {@link InputStream} to read.
   * @return the parsed {@link Song}.
   */
  public abstract Song read(InputStream inStream);

  /**
   * @param song the song to write.
   * @param outStream the {@link OutputStream} to write to.
   */
  public abstract void write(Song song, OutputStream outStream);

  /**
   * @param name the {@link #getName() name} of the requested {@link SongFormat}.
   * @return the {@link SongFormat} with the given {@link #getName() name} or {@code null} if not exists.
   */
  public static final SongFormat get(String name) {

    return FORMAT_MAP.get(name);
  }

  /**
   * @param beat
   * @return
   */
  Beat getUnitNoteLength(Beat beat) {

    return Beat.of(1, 4);
  }

  @Override
  public String toString() {

    return getName();
  }

}
