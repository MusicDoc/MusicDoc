package io.github.musicdoc.view.layout;

/**
 * TODO hohwille This type ...
 *
 */
public enum ViewPlacementType {

  /**
   * Placement for the {@link io.github.musicdoc.score.cell.ScoreCell#getLyric() lyric}s rendered below the
   * {@link #STAVE stave}.
   */
  LYRICS,

  /**
   * Placement for any item in the {@link io.github.musicdoc.stave.Stave} including
   * {@link io.github.musicdoc.decoration.MusicalDecoration}s.
   */
  STAVE,

  /**
   * Placement for a {@link io.github.musicdoc.harmony.chord.ChordSymbol} is placed top of the top-most item of the
   * {@link #STAVE stave}.
   */
  CHORD,

  /**
   * {@link io.github.musicdoc.volta.Volta} is placed on top of the {@link #STAVE stave} and the {@link #CHORD chords}.
   */
  VOLTA,

}
