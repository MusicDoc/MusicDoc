package io.github.musicdoc.music.score.line;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.score.cell.ScoreCell;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Implementation of {@link ScoreLine} that only has a {@link #getComment() comment}.
 */
public class ScoreCommentLine extends AbstractScoreLine {

  private final String comment;

  /**
   * The constructor.
   *
   * @param comment the {@link #getComment() comment}.
   */
  public ScoreCommentLine(String comment) {

    super();
    this.comment = comment;
  }

  @Override
  public String getComment() {

    return this.comment;
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

  @Override
  public int hashCode() {

    return Objects.hash(this.comment);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ScoreCommentLine other = (ScoreCommentLine) obj;
    return Objects.equals(this.comment, other.comment);
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append('%');
    sb.append(this.comment);
  }

}
