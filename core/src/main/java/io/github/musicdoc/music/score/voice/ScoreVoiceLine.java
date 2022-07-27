package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreLine} for a single voice (inside the {@link io.github.musicdoc.music.stave.Stave}, or with just the
 * lyrics below).
 */
public class ScoreVoiceLine extends ScoreLine<ScoreVoiceCell, ScoreVoiceLine> {

  private StaveVoice voice;

  /**
   * The constructor.
   */
  public ScoreVoiceLine() {

    super();
  }

  private ScoreVoiceLine(ScoreVoiceLine line, MutableObjecteCopier copier) {

    super(line, copier);
    this.voice = copier.copy(line.voice);
  }

  @Override
  public ScoreVoiceLine copy(MutableObjecteCopier copier) {

    return new ScoreVoiceLine(this, copier);
  }

  @Override
  public StaveVoice getVoice() {

    return this.voice;
  }

  /**
   * @param voice new value of {@link #getVoice()}.
   * @return a {@link ScoreVoiceLine} with the given {@link #getVoice() voice} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public ScoreVoiceLine setVoice(StaveVoice voice) {

    if (this.voice == voice) {
      return this;
    }
    ScoreVoiceLine line = makeMutable();
    line.voice = voice;
    return line;
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

}
