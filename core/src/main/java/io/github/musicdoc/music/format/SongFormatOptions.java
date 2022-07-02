package io.github.musicdoc.music.format;

import java.util.Objects;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.song.Song;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.tone.ToneNameStyle;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Bean to configure the options when formatting or parsing songs.
 */
public class SongFormatOptions {

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

  private Stave stave;

  private Song song;

  /**
   * The constructor.
   *
   * @param format the {@link SongFormat}.
   */
  public SongFormatOptions(SongFormat format) {

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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.harmony.chord.Chord}s, {@code false} otherwise.
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.harmony.chord.Chord#getExtensions() chord
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.decoration.MusicalDecoration}s, {@code false}
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.score.section.ScoreSection}s,
   *         {@code false} otherwise.
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.harmony.MusicalKey}s, {@code false} otherwise.
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
   * @return the current {@link Stave}.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @param stave new value of {@link #getStave()}.
   */
  public void setStave(Stave stave) {

    this.stave = stave;
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

}
