package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.format.FormatConstants;

/**
 * Defines how a {@link ScoreVoiceLine} continues its previous line.
 *
 * @see ScoreVoiceLine#getContinuation()
 */
public final class ScoreVoiceLineContinuation {

  /** The line continues with a new voice in the same stave. */
  public static final ScoreVoiceLineContinuation STAVE = new ScoreVoiceLineContinuation(
      FormatConstants.CONTINUE_STAVE);

  /** The line continues with a new stave or lyrics line below the stave. */
  public static final ScoreVoiceLineContinuation LINE = new ScoreVoiceLineContinuation(
      FormatConstants.CONTINUE_ROW);

  private final char symbol;

  private ScoreVoiceLineContinuation(char symbol) {

    super();
    this.symbol = symbol;
  }

  /**
   * @return the character symbol representing this {@link ScoreVoiceLineContinuation}.
   */
  public char getSymbol() {

    return this.symbol;
  }

  @Override
  public String toString() {

    return "" + this.symbol;
  }

  /**
   * @param c the {@link ScoreVoiceLineContinuation#getSymbol() symbol}.
   * @return the corresponding {@link ScoreVoiceLineContinuation}.
   */
  public static final ScoreVoiceLineContinuation of(char c) {

    if (c == STAVE.symbol) {
      return STAVE;
    } else if (c == LINE.symbol) {
      return LINE;
    }
    return null;
  }
}
