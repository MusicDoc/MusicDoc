package io.github.musicdoc.score.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteCopierSimple;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Implementation of {@link ScoreLine} as regular line for {@link StaveVoice}.
 */
public class ScoreVoiceLine extends AbstractScoreLine {

  private List<ScoreCell> cells;

  private StaveVoice voice;

  private boolean immutable;

  /**
   * The constructor.
   */
  public ScoreVoiceLine() {

    super();
    this.cells = new ArrayList<>();
  }

  private ScoreVoiceLine(ScoreVoiceLine line, MutableObjecteCopier copier) {

    super();
    this.voice = copier.copy(line.voice);
    this.cells = copier.copyList(line.cells);
  }

  @Override
  public ScoreVoiceLine copy() {

    return copy(MutableObjecteCopierSimple.INSTANCE);
  }

  @Override
  public ScoreVoiceLine copy(MutableObjecteCopier copier) {

    return new ScoreVoiceLine(this, copier);
  }

  @Override
  public String getComment() {

    return null;
  }

  @Override
  public List<ScoreCell> getCells() {

    return this.cells;
  }

  @Override
  public StaveVoice getVoice() {

    return this.voice;
  }

  @Override
  public ScoreVoiceLine setVoice(StaveVoice voice) {

    if (this.voice == voice) {
      return this;
    }
    ScoreVoiceLine line = makeMutable();
    line.voice = voice;
    return line;
  }

  @Override
  public ScoreVoiceLine makeMutable() {

    if (this.immutable) {
      return copy();
    } else {
      return this;
    }
  }

  @Override
  public ScoreVoiceLine transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreVoiceLine transposed = new ScoreVoiceLine();
    transposed.voice = this.voice;
    for (ScoreCell cell : this.cells) {
      transposed.cells.add(cell.transpose(steps, diatonic, context));
    }
    return transposed;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public ScoreVoiceLine makeImmutable() {

    if (!this.immutable) {
      this.voice.makeImmutable();
      this.cells = MutableObjecteHelper.makeImmutableRecursive(this.cells);
    }
    return this;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.cells, this.voice);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ScoreVoiceLine other = (ScoreVoiceLine) obj;
    if (!Objects.equals(this.cells, other.cells)) {
      return false;
    } else if (!Objects.equals(this.voice, other.voice)) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    for (ScoreCell cell : this.cells) {
      cell.toString(sb);
    }
  }

}
