package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreLine} for a single voice (inside the {@link io.github.musicdoc.music.stave.Stave}, or with just the
 * lyrics below).
 */
public class ScoreVoiceLine extends ScoreLine<ScoreVoiceCell, ScoreVoiceLine> {

  private ScoreVoiceLineContinuation continuation;

  /**
   * The constructor.
   */
  public ScoreVoiceLine() {

    super();
  }

  /**
   * @return the optional {@link ScoreVoiceLineContinuation continuation} of this line or {@code null}, if this is
   *         the first line of a new {@link io.github.musicdoc.music.score.ScoreRow row}.
   */
  public ScoreVoiceLineContinuation getContinuation() {

    return this.continuation;
  }

  @Override
  public boolean isContinueRow() {

    return (this.continuation != null);
  }

  /**
   * @param continuation the new value of {@link #getContinuation()}.
   */
  public void setContinuation(ScoreVoiceLineContinuation continuation) {

    this.continuation = continuation;
  }

  /**
   * @param cell the {@link ScoreVoiceCell} to add to {@link #getCells() cells}.
   */
  public void addCell(ScoreVoiceCell cell) {

    this.cells.add(cell);
  }

  @Override
  protected ScoreVoiceCell createCell() {

    return new ScoreVoiceCell();
  }

  @Override
  public ScoreVoiceLine transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreVoiceLine transposed = new ScoreVoiceLine();
    for (ScoreVoiceCell cell : this.cells) {
      transposed.cells.add(cell.transpose(steps, diatonic, context));
    }
    return transposed;
  }

  /**
   * @param voiceLine the {@link ScoreVoiceLine} to join (append).
   */
  public void join(ScoreVoiceLine voiceLine) {

    assert (this.continuation != null);
    int len = this.cells.size();
    int otherLen = voiceLine.cells.size();
    if (otherLen < len) {
      len = otherLen;
    }
    for (int i = 0; i < len; i++) {
      ScoreVoiceCell otherCell = voiceLine.cells.get(i);
      Stave otherStave = otherCell.getStave();
      if (otherStave != null) {
        ScoreVoiceCell myCell = this.cells.get(i);
        Stave myStave = myCell.getStave();
        if (this.continuation == ScoreVoiceLineContinuation.STAVE) {
          otherStave.join(myStave, true);
          myCell.setStave(otherStave);
        } else if (this.continuation == ScoreVoiceLineContinuation.LINE) {
          myStave.join(otherStave, false);
        }
      }
    }
  }
}
