package io.github.musicdoc.score.line;

/**
 * A {@link ScoreLineBreak} indicates a position where to break the entire
 * {@link io.github.musicdoc.stave.system.StaveSystem}. So in case of a break all
 * {@link io.github.musicdoc.stave.Stave}s will end at this position and will be continued in a new
 * {@link io.github.musicdoc.stave.Stave}s below in the {@link io.github.musicdoc.score.Score}. A {@link ScoreLineBreak}
 * shall always be placed in the first {@link ScoreLine} of a {@link io.github.musicdoc.stave.system.StaveSystem}. It
 * may be repeated by following {@link ScoreLine}s at the same position.
 *
 * @see io.github.musicdoc.score.cell.ScoreCell#getLineBreak()
 */
public enum ScoreLineBreak {

  /** A hard break that will always break at this position. */
  BREAK,

  /**
   * A soft break that suggests to break at this position but if a significant amount of space is available it can be
   * used and an automatic break will be done when the remaining space is used.
   */
  SOFT_BREAK;

}
