package io.github.musicdoc.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.system.StaveSystemContainer;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Represents the entire score of a {@link io.github.musicdoc.song.Song} with potential lyrics, chords, scales,
 * tabs, etc. in a structured way. It contains out of multiple {@link #getSections() sections}.
 *
 * @see io.github.musicdoc.song.Song#Score
 */
public class Score extends AbstractTransposable<Score> implements StaveSystemContainer, MutableObject<Score> {

  private List<ScoreSection> sections;

  private StaveSystem staveSystem;

  private boolean immutable;

  /**
   * The constructor.
   */
  public Score() {

    super();
    this.sections = new ArrayList<>();
  }

  private Score(Score score, MutableObjecteCopier copier) {

    super();
    this.sections = copier.copyList(this.sections);
    this.staveSystem = score.staveSystem;
  }

  @Override
  public Score copy(MutableObjecteCopier copier) {

    return new Score(this, copier);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public Score makeImmutable() {

    if (!this.immutable) {
      this.immutable = true;
      this.sections = MutableObjecteHelper.makeImmutableRecursive(this.sections);
    }
    return this;
  }

  /**
   * @return the {@link List} of {@link ScoreSection}.
   */
  public List<ScoreSection> getSections() {

    return this.sections;
  }

  /**
   * @param section the {@link ScoreSection} to add to the {@link #getSections() sections}.
   */
  public void addSection(ScoreSection section) {

    this.sections.add(section);
  }

  /**
   * @param i the index of the requested {@link ScoreSection}.
   * @return the {@link ScoreSection} at the given index or {@code null} if no such section exists.
   * @see #getSections()
   * @see List#get(int)
   */
  public ScoreSection getSection(int i) {

    if (i >= this.sections.size()) {
      return null;
    }
    return this.sections.get(i);
  }

  @Override
  public StaveSystem getStaveSystem() {

    return this.staveSystem;
  }

  @Override
  public Score setStaveSystem(StaveSystem staveSystem) {

    if (this.staveSystem == staveSystem) {
      return this;
    }
    Score score = makeMutable();
    score.staveSystem = staveSystem;
    return score;
  }

  /**
   * @return {@code true} if the last {@link ScoreSection} was empty and has been {@link List#remove(int) removed} from
   *         the {@link #getSections() sections}.
   */
  public boolean removeLastSectionIfEmpty() {

    int last = this.sections.size() - 1;
    if (last >= 0) {
      ScoreSection section = this.sections.get(last);
      if (section.isEmpty()) {
        this.sections.remove(last);
        return true;
      }
    }
    return false;
  }

  @Override
  public Score transpose(int steps, boolean diatonic, TransposeContext context) {

    Score score = new Score();
    for (ScoreSection section : this.sections) {
      score.sections.add(section.transpose(steps, diatonic, context));
    }
    return score;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.staveSystem != null) {
      this.staveSystem.toString(sb);
      sb.append('\n');
    }
    for (ScoreSection section : this.sections) {
      section.toString(sb);
    }
  }
}
