package io.github.musicdoc.music.partiture.voice;

import io.github.musicdoc.music.partiture.PartitureLine;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link PartitureLine} for a single voice (inside the {@link io.github.musicdoc.music.stave.Stave}, or with just the
 * lyrics below).
 */
public class PartitureVoiceLine extends PartitureLine<PartitureVoiceCell, PartitureVoiceLine> {

  private PartitureVoiceLineContinuation continuation;

  /**
   * The constructor.
   */
  public PartitureVoiceLine() {

    super();
  }

  /**
   * @return the optional {@link PartitureVoiceLineContinuation continuation} of this line or {@code null}, if this is
   *         the first line of a new {@link io.github.musicdoc.music.partiture.PartitureRow row}.
   */
  public PartitureVoiceLineContinuation getContinuation() {

    return this.continuation;
  }

  @Override
  public boolean isContinueRow() {

    return (this.continuation != null);
  }

  public void setContinuation(PartitureVoiceLineContinuation continuation) {

    this.continuation = continuation;
  }

  public void addCell(PartitureVoiceCell cell) {

    this.cells.add(cell);
  }

  @Override
  protected PartitureVoiceCell createCell() {

    return new PartitureVoiceCell();
  }

  @Override
  public PartitureVoiceLine transpose(int steps, boolean diatonic, TransposeContext context) {

    PartitureVoiceLine transposed = new PartitureVoiceLine();
    for (PartitureVoiceCell cell : this.cells) {
      transposed.cells.add(cell.transpose(steps, diatonic, context));
    }
    return transposed;
  }

  /**
   * @param voiceLine the {@link PartitureVoiceLine} to join (append).
   */
  public void join(PartitureVoiceLine voiceLine) {

    assert (this.continuation != null);
    int len = this.cells.size();
    int otherLen = voiceLine.cells.size();
    if (otherLen < len) {
      len = otherLen;
    }
    for (int i = 0; i < len; i++) {
      PartitureVoiceCell otherCell = voiceLine.cells.get(i);
      Stave otherStave = otherCell.getStave();
      if (otherStave != null) {
        PartitureVoiceCell myCell = this.cells.get(i);
        Stave myStave = myCell.getStave();
        if (this.continuation == PartitureVoiceLineContinuation.STAVE) {
          otherStave.join(myStave, true);
          myCell.setStave(otherStave);
        } else if (this.continuation == PartitureVoiceLineContinuation.LINE) {
          myStave.join(otherStave, false);
        }
      }
    }
  }
}
