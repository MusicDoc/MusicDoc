package io.github.musicdoc.score.line;

import java.util.Collections;
import java.util.List;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Implementation of {@link ScoreLine} that only has a {@link #getComment() comment}.
 */
public class ScoreEmptyLine extends AbstractScoreLine {

  /** Singleton instance */
  public static final ScoreEmptyLine INSTANCE = new ScoreEmptyLine();

  private ScoreEmptyLine() {

    super();
  }

  @Override
  public String getComment() {

    return "";
  }

  @Override
  public List<ScoreCell> getCells() {

    return Collections.emptyList();
  }

  @Override
  public StaveVoice getVoice() {

    return null;
  }

  @Override
  public ScoreLine setVoice(StaveVoice voice) {

    throw new IllegalStateException();
  }

  @Override
  public ScoreLine transpose(int steps, boolean diatonic, TransposeContext context) {

    return this;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append("");
  }

  @Override
  public boolean isImmutable() {

    return true;
  }

  @Override
  public ScoreLine makeImmutable() {

    return this;
  }

  @Override
  public ScoreLine copy(MutableObjecteCopier copier) {

    return this;
  }

}
