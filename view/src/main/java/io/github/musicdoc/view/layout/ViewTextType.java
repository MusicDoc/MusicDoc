package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.model.ViewItemText;

/**
 * Enumeration with available types of {@link ViewItemText}.
 */
public enum ViewTextType {

  /**
   * Musical symbols for stave content like {@link io.github.musicdoc.note.Note},
   * {@link io.github.musicdoc.rhythm.rest.Rest}, etc.
   */
  MUSIC_CONTENT,

  /**
   * Musical symbols for declarations like {@link io.github.musicdoc.clef.Clef},
   * {@link io.github.musicdoc.stave.StaveBracket}, etc.
   */
  MUSIC_DECLARATION,

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

  /** Tablature (fret numbers, hammer on, pull off, etc.). */
  TAB,

  /** {@link io.github.musicdoc.volta.Volta} {@link io.github.musicdoc.volta.Volta#getNumbers() numbers}. */
  VOLTA,

  /**
   * {@link io.github.musicdoc.stave.voice.StaveVoice} {@link io.github.musicdoc.stave.voice.StaveVoice#getName() name}
   * and {@link io.github.musicdoc.stave.voice.StaveVoice#getAbbreviation() abbreviation} as well as
   * {@link io.github.musicdoc.stave.system.StaveSystem} {@link io.github.musicdoc.stave.system.StaveSystem#getName()
   * name}.
   */
  LABEL;

  /**
   * @return the corresponding {@link ViewPlacementType} or {@code null} for {@link ViewPlacement#ABSOLUTE}.
   */
  public ViewPlacementType asPlacementType() {

    switch (this) {
      case CHORD_SYMBOL:
        return ViewPlacementType.CHORD;
      case LYRICS:
        return ViewPlacementType.LYRICS;
      case MUSIC_CONTENT:
      case TAB:
        return ViewPlacementType.STAVE;
      case VOLTA:
        return ViewPlacementType.VOLTA;
      default:
        return null;
    }
  }

  /**
   * @return the corresponding {@link ViewTextProgression}.
   */
  public ViewTextProgression asTextProgression() {

    switch (this) {
      case TITLE:
      case SUB_TITLE:
        return ViewTextProgression.NEWLINE;
      default:
        return ViewTextProgression.ABSOLUTE;
    }
  }

  /**
   * @return the corresponding {@link ViewTextAlignment}.
   */
  public ViewTextAlignment asTextAlignment() {

    switch (this) {
      case TITLE:
      case SUB_TITLE:
        return ViewTextAlignment.CENTER;
      default:
        return ViewTextAlignment.LEFT;
    }
  }

}
