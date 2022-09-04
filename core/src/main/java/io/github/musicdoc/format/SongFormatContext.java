package io.github.musicdoc.format;

import java.util.Objects;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.tuplet.Tuplet;
import io.github.musicdoc.rhythm.tuplet.TupletContext;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.AbstractStave;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;
import io.github.musicdoc.tone.ToneNameStyle;
import io.github.musicdoc.tone.pitch.TonePitch;
import io.github.musicdoc.tone.pitch.TonePitchChange;
import io.github.musicdoc.tone.pitch.TonePitchEnglish;

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

  private Metre metre;

  private Clef clef;

  private AbstractStave<?> stave;

  private StaveVoice staveVoice;

  private StaveSystem staveSystem;

  private StaveVoiceContainer staveVoiceContainer;

  private TupletContext tupletContext;

  private final TonePitchChange tonePitchChange;

  private Song song;

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
    this.tonePitchChange = new TonePitchChange();
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
   * @return {@code true} to normalize {@link io.github.musicdoc.harmony.chord.Chord#getExtensions() chord extensions},
   *         {@code false} otherwise.
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
   * @return {@code true} to normalize {@link io.github.musicdoc.score.section.ScoreSection}s, {@code false} otherwise.
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
   * @return {@code true} to normalize {@link io.github.musicdoc.harmony.key.MusicalKey}s, {@code false} otherwise.
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
      Clef newClef = stave.getClef();
      if (newClef != null) {
        setClef(newClef);
      }
      MusicalKey newKey = stave.getKey();
      if (newKey != null) {
        setKey(newKey);
      }
      Metre newMetre = stave.getMetre();
      if (newMetre != null) {
        setMetre(newMetre);
      }
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
   * @return the {@link TonePitchChange} used to {@link TonePitchChange#resolve(io.github.musicdoc.tone.pitch.TonePitch)
   *         resolve} relative {@link TonePitch}es.
   */
  public TonePitchChange getTonePitchChange() {

    return this.tonePitchChange;
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
    if (key != null) {
      this.tonePitchChange.setKey(key);
    }
  }

  /**
   * @return the current {@link Metre}.
   */
  public Metre getMetre() {

    return this.metre;
  }

  /**
   * @param beat new value of {@link #getMetre()}.
   */
  public void setMetre(Metre beat) {

    this.metre = beat;
  }

  /**
   * @return the {@link Song#unitNoteLength}.
   */
  public PlainFraction getUnitNoteLength() {

    if (this.song == null) {
      return this.format.getUnitNoteLength(null);
    }
    PlainFraction unitNoteLength = this.song.unitNoteLength.getValue();
    if (unitNoteLength == null) {
      Metre songBeat = this.song.metre.getValue();
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
   * @return the {@link TupletContext} for parsing {@link Tuplet}s.
   */
  public TupletContext getTupletContext() {

    return this.tupletContext;
  }

  /**
   * @param tupletContext new value of {@link #getTupletContext()}.
   */
  public void setTupletContext(TupletContext tupletContext) {

    this.tupletContext = tupletContext;
  }

  /**
   * @return the {@link SongFormat}.
   */
  public SongFormat getFormat() {

    return this.format;
  }

}
