package io.github.musicdoc.format;

import java.util.Locale;

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
import io.github.musicdoc.rhythm.fraction.PlainFractionMapper;
import io.github.musicdoc.rhythm.item.ValuedItemMapper;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.rhythm.rest.RestMapper;
import io.github.musicdoc.rhythm.tempo.TempoMapper;
import io.github.musicdoc.rhythm.value.MusicalValueMapper;
import io.github.musicdoc.rhythm.value.variation.MusicalValueVariationMapper;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreMapper;
import io.github.musicdoc.score.line.ScoreLineMapper;
import io.github.musicdoc.score.section.ScoreSectionNameMapper;
import io.github.musicdoc.stave.StaveChangeMapper;
import io.github.musicdoc.stave.StaveMapper;
import io.github.musicdoc.stave.system.StaveSystemMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;
import io.github.musicdoc.tone.ToneMapper;
import io.github.musicdoc.tone.pitch.TonePitchMapper;
import io.github.musicdoc.volta.VoltaMapper;

/**
 * Abstract base class for a mapper that is both a {@link MusicWriter} and a {@link MusicReader}.
 *
 * @param <T> type of the musical object to map.
 */
public abstract class AbstractMapper<T> implements Mapper<T>, FormatConstants {

  /**
   * @return the {@link SongFormat}.
   */
  protected abstract SongFormat getFormat();

  /**
   * Gives access to {@link #getFormat()}.
   *
   * @param mapper the {@link AbstractMapper}.
   * @return the {@link #getFormat() format} of the given {@link AbstractMapper}.
   */
  protected SongFormat getFormat(AbstractMapper<?> mapper) {

    if (mapper == null) {
      return null;
    }
    return mapper.getFormat();
  }

  @Override
  public String write(T object) {

    return write(object, new SongFormatContext(getFormat()));
  }

  @Override
  public String write(T object, SongFormatContext context) {

    StringBuilder buffer = new StringBuilder();
    MusicOutputStream out = getFormat().createOutputStream(buffer);
    write(object, out, context);
    out.close();
    return buffer.toString();
  }

  @Override
  public T read(String string) {

    return read(getFormat().createInputStream(string));
  }

  @Override
  public T read(MusicInputStream chars) {

    return read(chars, new SongFormatContext(getFormat()));
  }

  /**
   * @param string the {@link String} to convert.
   * @return the given {@link String} converted to lower case without {@link Locale} side-effects.
   */
  protected static String toLowerCase(String string) {

    if (string == null) {
      return null;
    }
    return string.toLowerCase(Locale.ROOT);
  }

  /**
   * @return the {@link TonalSystemMapper}.
   */
  protected TonalSystemMapper getTonalSystemMapper() {

    return getFormat().getTonalSystemMapper();
  }

  /**
   * @return the {@link TonePitchMapper}.
   */
  protected TonePitchMapper getTonePitchMapper() {

    return getFormat().getTonePitchMapper();
  }

  /**
   * @return the {@link ToneMapper}.
   */
  protected ToneMapper getToneMapper() {

    return getFormat().getToneMapper();
  }

  /**
   * @return the {@link ChordMapper}.
   */
  protected ChordMapper getChordMapper() {

    return getFormat().getChordMapper();
  }

  /**
   * @return the {@link ChordExtensionMapper}.
   */
  protected ChordExtensionMapper getChordExtensionMapper() {

    return getFormat().getChordExtensionMapper();
  }

  /**
   * @return the {@link ChordContainerMapper}.
   */
  protected ChordContainerMapper getChordContainerMapper() {

    return getFormat().getChordContainerMapper();
  }

  /**
   * @return the {@link MusicalKeyMapper}.
   */
  protected MusicalKeyMapper getKeyMapper() {

    return getFormat().getKeyMapper();
  }

  /**
   * @return the {@link MetreMapper}.
   */
  protected MetreMapper getBeatMapper() {

    return getFormat().getBeatMapper();
  }

  /**
   * @return the {@link PlainFractionMapper}.
   */
  protected PlainFractionMapper getPlainFractionMapper() {

    return getFormat().getPlainFractionMapper();
  }

  /**
   * @return the {@link TempoMapper}.
   */
  protected TempoMapper getTempoMapper() {

    return getFormat().getTempoMapper();
  }

  /**
   * @return the {@link BarLineMapperMusicDoc}.
   */
  protected BarLineMapper getBarLineMapper() {

    return getFormat().getBarLineMapper();
  }

  /**
   * @return the {@link BarLineTypeMapper}.
   */
  protected BarLineTypeMapper getBarLineTypeMapper() {

    return getFormat().getBarLineTypeMapper();
  }

  /**
   * @return the {@link VoltaMapper}.
   */
  protected VoltaMapper getVoltaMapper() {

    return getFormat().getVoltaMapper();
  }

  /**
   * @return the {@link StaveMapper}.
   */
  protected StaveMapper getStaveMapper() {

    return getFormat().getStaveMapper();
  }

  /**
   * @return the {@link StaveChangeMapper}.
   */
  protected StaveChangeMapper getStaveChangeMapper() {

    return getFormat().getStaveChangeMapper();
  }

  /**
   * @return the {@link StaveSystemMapper}.
   */
  protected StaveSystemMapper getStaveSystemMapper() {

    return getFormat().getStaveGroupMapper();
  }

  /**
   * @return the {@link ValuedItemMapper}.
   */
  protected ValuedItemMapper getValuedItemMapper() {

    return getFormat().getValuedItemMapper();
  }

  /**
   * @return the {@link MusicalValueMapper}.
   */
  protected MusicalValueMapper getValueMapper() {

    return getFormat().getValueMapper();
  }

  /**
   * @return the {@link MusicalValueVariationMapper}.
   */
  protected MusicalValueVariationMapper getVariationMapper() {

    return getFormat().getVariationMapper();
  }

  /**
   * @return the {@link RestMapper}.
   */
  protected RestMapper getRestMapper() {

    return getFormat().getRestMapper();
  }

  /**
   * @return the {@link NoteMapper}.
   */
  protected NoteMapper getNoteMapper() {

    return getFormat().getNoteMapper();
  }

  /**
   * @return the {@link MusicalDecorationMapper}.
   */
  protected MusicalDecorationMapper getDecorationMapper() {

    return getFormat().getDecorationMapper();
  }

  /**
   * @return the {@link InstrumentMapper}.
   */
  protected InstrumentMapper getInstrumentMapper() {

    return getFormat().getInstrumentMapper();
  }

  /**
   * @return the {@link StemDirectionMapper}.
   */
  protected StemDirectionMapper getStemDirectionMapper() {

    return getFormat().getStemDirectionMapper();
  }

  /**
   * @return the {@link ClefMapper}.
   */
  protected ClefMapper getClefMapper() {

    return getFormat().getClefMapper();
  }

  /**
   * @return the {@link StaveVoiceMapper}.
   */
  protected StaveVoiceMapper getStaveVoiceMapper() {

    return getFormat().getStaveVoiceMapper();
  }

  /**
   * @return the {@link ScoreSectionNameMapper}.
   */
  protected ScoreSectionNameMapper getSectionNameMapper() {

    return getFormat().getSectionNameMapper();
  }

  /**
   * @return the {@link ScoreLineMapper}.
   */
  protected ScoreLineMapper getScoreLineMapper() {

    return getFormat().getScoreLineMapper();
  }

  /**
   * @return the underlying {@link ScoreMapper} to parse and format {@link Score}s.
   */
  protected ScoreMapper getScoreMapper() {

    return getFormat().getScoreMapper();
  }

}
