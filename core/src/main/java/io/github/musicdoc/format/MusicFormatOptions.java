package io.github.musicdoc.format;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.tone.ToneNameStyle;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Bean to configure the options when formatting or parsing songs.
 */
public class MusicFormatOptions {

  private boolean normalizeChords;

  private boolean normalizeChordExtensions;

  private boolean normalizeItemDecorations;

  private boolean normalizeMusicalKeys;

  private boolean normalizeSections;

  private ToneNameStyle<?> toneNameStyle;

  private MusicalKey key;

  private Beat beat;

  private Beat unitNoteLength;

  private Clef clef;

  private SongFormat format;

  /**
   * The constructor.
   */
  public MusicFormatOptions() {

    super();
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.partiture.section.PartitureSection}s,
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
   * @return the {@link SongFormat}.
   */
  public SongFormat getFormat() {

    return this.format;
  }

  /**
   * @param format new value of {@link #getFormat()}.
   */
  void setFormat(SongFormat format) {

    this.format = format;
  }
}
