package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.model.ViewItemText;

/**
 * Enumeration with available types of {@link ViewItemText}.
 */
public enum ViewTextType {

  /** Musical symbols like notes, rest, clefs, etc. */
  MUSIC,

  /** Lyrics with the song text in regular font. */
  LYRICS,

  /** Header information for song title. */
  TITLE,

  /** Header information for sub-titles (artist, composer, capo, etc.). */
  SUB_TITLE,

  /** Footer line with page number. */
  FOOTER,

  /** {@link io.github.musicdoc.score.section.ScoreSectionName}. */
  SECTION,

  /** {@link io.github.musicdoc.harmony.chord.ChordContainer} with chord symbols. */
  CHORD_SYMBOL,

  /** {@link io.github.musicdoc.volta.Volta} {@link io.github.musicdoc.volta.Volta#getNumbers() numbers}. */
  VOLTA,

  /**
   * {@link io.github.musicdoc.stave.voice.StaveVoice} {@link io.github.musicdoc.stave.voice.StaveVoice#getName() name}
   * and {@link io.github.musicdoc.stave.voice.StaveVoice#getAbbreviation() abbreviation} as well as
   * {@link io.github.musicdoc.stave.system.StaveSystem} {@link io.github.musicdoc.stave.system.StaveSystem#getName()
   * name}.
   */
  VOICE_OR_STAVE,

}
