package io.github.musicdoc.format;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.github.musicdoc.bar.BarLineMapper;
import io.github.musicdoc.bar.BarLineMapperMusicDoc;
import io.github.musicdoc.bar.BarLineTypeMapper;
import io.github.musicdoc.clef.ClefMapper;
import io.github.musicdoc.decoration.MusicalDecorationMapper;
import io.github.musicdoc.harmony.TonalSystemMapper;
import io.github.musicdoc.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.harmony.chord.ChordExtensionMapper;
import io.github.musicdoc.harmony.chord.ChordMapper;
import io.github.musicdoc.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.instrument.InstrumentMapper;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.note.NoteMapper;
import io.github.musicdoc.note.StemDirectionMapper;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.fraction.PlainFractionMapper;
import io.github.musicdoc.rhythm.item.ValuedItemMapper;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.rhythm.punctuation.PunctuationMapper;
import io.github.musicdoc.rhythm.rest.RestMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapper;
import io.github.musicdoc.rhythm.tuplet.TupletMapper;
import io.github.musicdoc.rhythm.value.MusicalValueMapper;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreMapper;
import io.github.musicdoc.score.line.ScoreLineBreakMapper;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.song.SongMapper;
import io.github.musicdoc.stave.StaveChangeMapper;
import io.github.musicdoc.stave.StaveMapper;
import io.github.musicdoc.stave.system.StaveSystemMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.tone.ToneMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapper;
import io.github.musicdoc.volta.VoltaMapper;

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
   * @return the {@link MetreMapper}.
   */
  protected abstract MetreMapper getBeatMapper();

  /**
   * @return the {@link PlainFractionMapper}.
   */
  protected abstract PlainFractionMapper getPlainFractionMapper();

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
   * @return the {@link PunctuationMapper}.
   */
  protected abstract PunctuationMapper getPunctuationMapper();

  /**
   * @return the {@link TupletMapper}.
   */
  protected abstract TupletMapper getTupletMapper();

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
   * @return the {@link ScoreLineBreakMapper}.
   */
  protected abstract ScoreLineBreakMapper getScoreLineBreakMapper();

  /**
   * @return the name of this formatSection.
   */
  public abstract String getName();

  /**
   * @return the default file extension of this format excluding the dot (e.g. "musicdoc").
   */
  public String getExtension() {

    return getName().toLowerCase(Locale.ROOT);
  }

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
   * @param song the song to write.
   * @return the written {@link Song} as {@link String}.
   */
  public abstract String write(Song song);

  /**
   * @param name the {@link #getName() name} of the requested {@link SongFormat}.
   * @return the {@link SongFormat} with the given {@link #getName() name} or {@code null} if not exists.
   */
  public static final SongFormat get(String name) {

    return FORMAT_MAP.get(name);
  }

  /**
   * @param beat the current beat.
   * @return the default for {@link Song#unitNoteLength}.
   */
  PlainFraction getUnitNoteLength(Metre beat) {

    return PlainFraction._1_1;
  }

  /**
   * @return {@code true} if {@link io.github.musicdoc.score.cell.ScoreCell#getItem() items} are supported,
   *         {@code false} otherwise.
   */
  protected boolean isSupportItem() {

    return true;
  }

  /**
   * @return {@code true} if {@link io.github.musicdoc.score.Score#getSections() sections} are supported, {@code false}
   *         otherwise. If not supported there will always be a single
   *         {@link io.github.musicdoc.score.section.ScoreSection} without a
   *         {@link io.github.musicdoc.score.section.ScoreSection#getName() name}.
   */
  protected boolean isSupportSection() {

    return true;
  }

  @Override
  public String toString() {

    return getName();
  }

}
