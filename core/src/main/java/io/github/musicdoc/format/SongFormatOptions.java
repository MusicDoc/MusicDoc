package io.github.musicdoc.format;

import io.github.musicdoc.music.tone.ToneNameStyle;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Bean to configure the options when parsing songs.
 */
public class SongFormatOptions {

  private boolean normalizeChords;

  private boolean normalizeChordExtensions;

  private boolean normalizeItemDecorations;

  private boolean normalizeMusicalKeys;

  private boolean normalizeSections;

  private ToneNameStyle<?> toneNameStyle;

  /**
   * The constructor.
   */
  public SongFormatOptions() {

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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.harmony.Chord}s, {@code false} otherwise.
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
   * @return {@code true} to normalize {@link io.github.musicdoc.music.harmony.Chord#getExtensions() chord extensions},
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
}
