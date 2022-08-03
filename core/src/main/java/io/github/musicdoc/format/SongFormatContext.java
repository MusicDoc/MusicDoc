package io.github.musicdoc.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rythm.beat.Beat;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.AbstractStave;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;
import io.github.musicdoc.tone.ToneNameStyle;
import io.github.musicdoc.tone.TonePitchEnglish;

/**
 * Bean with the context and options when reading or writing songs. All properties will initially be {@code null} and
 * hence getters may return {@code null}.
 */
public class SongFormatContext {

  private final SongFormat format;

  private boolean normalizeChords;

  private boolean normalizeChordExtensions;

  private boolean normalizeItemDecorations;

  private boolean normalizeMusicalKeys;

  private boolean normalizeSections;

  private ToneNameStyle<?> toneNameStyle;

  private MusicalKey key;

  private Beat beat;

  private Clef clef;

  private AbstractStave<?> stave;

  private StaveVoice staveVoice;

  private StaveSystem staveSystem;

  private StaveVoiceContainer staveVoiceContainer;

  private Song song;

  private Map<String, Object> properties;

  /**
   * The constructor.
   *
   * @param format the {@link SongFormat}.
   */
  public SongFormatContext(SongFormat format) {

    super();
    Objects.requireNonNull(format);
    this.format = format;
    this.toneNameStyle = TonePitchEnglish.STYLE;
  }

  /**
   * @return the {@link ToneNameStyle}.
   */
  public ToneNameStyle<?> getToneNameStyle() {

    return this.toneNameStyle;
  }

  /**
   * @param toneNameStyle the new value of {@link #getToneNameStyle()}.
   */
  public void setToneNameStyle(ToneNameStyle<?> toneNameStyle) {

    this.toneNameStyle = toneNameStyle;
  }

  /**
   * @return {@code true} to normalize {@link io.github.musicdoc.harmony.chord.Chord}s, {@code false} otherwise.
   */
  public boolean isNormalizeChords() {

    return this.normalizeChords;
  }

  /**
   * @param normalizeChords the new value of {@link #isNormalizeChords()}.
   */
  public void setNormalizeChords(boolean normalizeChords) {

    this.normalizeChords = normalizeChords;
  }

  /**
   * @return {@code true} to normalize {@link io.github.musicdoc.harmony.chord.Chord#getExtensions() chord
   *         extensions}, {@code false} otherwise.
   */
  public boolean isNormalizeChordExtensions() {

    return this.normalizeChordExtensions;
  }

  /**
   * @param normalizeChordExtensions the new value of {@link #isNormalizeChordExtensions()}.
   */
  public void setNormalizeChordExtensions(boolean normalizeChordExtensions) {

    this.normalizeChordExtensions = normalizeChordExtensions;
  }

  /**
   * @return {@code true} to normalize {@link io.github.musicdoc.decoration.MusicalDecoration}s, {@code false}
   *         otherwise.
   */
  public boolean isNormalizeItemDecorations() {

    return this.normalizeItemDecorations;
  }

  /**
   * @param normalizeItemDecorations the new value of {@link #isNormalizeItemDecorations()}.
   */
  public void setNormalizeItemDecorations(boolean normalizeItemDecorations) {

    this.normalizeItemDecorations = normalizeItemDecorations;
  }

  /**
   * @return {@code true} to normalize {@link io.github.musicdoc.score.section.ScoreSection}s, {@code false}
   *         otherwise.
   */
  public boolean isNormalizeSections() {

    return this.normalizeSections;
  }

  /**
   * @param normalizeSections the new value of {@link #isNormalizeSections()}, {@code false} otherwise.
   */
  public void setNormalizeSections(boolean normalizeSections) {

    this.normalizeSections = normalizeSections;
  }

  /**
   * @return {@code true} to normalize {@link io.github.musicdoc.harmony.key.MusicalKey}s, {@code false}
   *         otherwise.
   */
  public boolean isNormalizeMusicalKeys() {

    return this.normalizeMusicalKeys;
  }

  /**
   * @param normalizeMusicalKeys the new value of {@link #isNormalizeMusicalKeys()}.
   */
  public void setNormalizeMusicalKeys(boolean normalizeMusicalKeys) {

    this.normalizeMusicalKeys = normalizeMusicalKeys;
  }

  /**
   * @return the current {@link AbstractStave}.
   */
  public AbstractStave<?> getStave() {

    return this.stave;
  }

  /**
   * @param stave new value of {@link #getStave()}.
   */
  public void setStave(AbstractStave<?> stave) {

    this.stave = stave;
    if (stave != null) {
      setClef(stave.getClef());
      setKey(stave.getKey());
      setBeat(stave.getBeat());
    }
  }

  /**
   * @return the current {@link StaveVoice}.
   */
  public StaveVoice getStaveVoice() {

    return this.staveVoice;
  }

  /**
   * @param staveVoice new value of {@link #getStaveVoice()}.
   */
  public void setStaveVoice(StaveVoice staveVoice) {

    this.staveVoice = staveVoice;
  }

  /**
   * @return the current {@link StaveSystem}.
   */
  public StaveSystem getStaveSystem() {

    return this.staveSystem;
  }

  /**
   * @param staveSystem new value of {@link #getStaveSystem()}.
   */
  public void setStaveSystem(StaveSystem staveSystem) {

    this.staveSystem = staveSystem;
  }

  /**
   * @return the {@link StaveVoiceContainer} with the state to determine the next voice if no
   *         {@link StaveVoiceContainer#getVoice(String) voice ID} is given.
   */
  public StaveVoiceContainer getStaveVoiceContainer() {

    return this.staveVoiceContainer;
  }

  /**
   * @param staveVoiceContainer new value of {@link #getStaveVoiceContainer()}.
   */
  public void setStaveVoiceContainer(StaveVoiceContainer staveVoiceContainer) {

    this.staveVoiceContainer = staveVoiceContainer;
  }

  /**
   * @return the current {@link Clef}.
   */
  public Clef getClef() {

    return this.clef;
  }

  /**
   * @param clef new value of {@link #getClef()}.
   */
  public void setClef(Clef clef) {

    this.clef = clef;
  }

  /**
   * @return the current {@link MusicalKey}.
   */
  public MusicalKey getKey() {

    return this.key;
  }

  /**
   * @param key new value of {@link #getKey()}.
   */
  public void setKey(MusicalKey key) {

    this.key = key;
  }

  /**
   * @return the current {@link Beat}.
   */
  public Beat getBeat() {

    return this.beat;
  }

  /**
   * @param beat new value of {@link #getBeat()}.
   */
  public void setBeat(Beat beat) {

    this.beat = beat;
  }

  /**
   * @return the {@link Song#unitNoteLength}.
   */
  public Beat getUnitNoteLength() {

    if (this.song == null) {
      return this.format.getUnitNoteLength(null);
    }
    Beat unitNoteLength = this.song.unitNoteLength.getValue();
    if (unitNoteLength == null) {
      Beat songBeat = this.song.beat.getValue();
      unitNoteLength = this.format.getUnitNoteLength(songBeat);
    }
    return unitNoteLength;
  }

  /**
   * @return the current {@link Song}.
   */
  public Song getSong() {

    return this.song;
  }

  /**
   * @param song new value of {@link #getSong()}.
   */
  public void setSong(Song song) {

    this.song = song;
  }

  /**
   * @return the {@link SongFormat}.
   */
  public SongFormat getFormat() {

    return this.format;
  }

  /**
   * @return a generic {@link Map} of current properties.
   */
  public Map<String, Object> getProperties() {

    if (this.properties == null) {
      this.properties = new HashMap<>();
    }
    return this.properties;
  }

  /**
   * @param <T> type of the property value.
   * @param name the name of the requested property.
   * @param defaultValue the default value to return if the property is undefined ({@code null}). May be {@code null}.
   * @param type the {@link Class} reflecting the property value. May be {@code null}.
   * @return the value of the specified property.
   */
  @SuppressWarnings("unchecked")
  public <T> T getProperty(String name, T defaultValue, Class<T> type) {

    Object value = getProperties().get(name);
    if (value == null) {
      return defaultValue;
    }
    if (type == null) {
      return (T) value;
    } else {
      return type.cast(value);
    }
  }

  /**
   * @param name the name of the property to set.
   * @param value the new value of the specified property.
   * @return the previous value of the property.
   */
  public Object setProperty(String name, Object value) {

    return getProperties().put(name, value);
  }

}
