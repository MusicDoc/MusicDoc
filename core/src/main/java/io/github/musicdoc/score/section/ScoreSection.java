package io.github.musicdoc.score.section;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.system.StaveSystemContainer;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Represents a logical section of a {@link Score}. Each section has a {@link #getName() name} such as "Verse 1" and
 * multiple {@link #getRows() rows}.
 */
public class ScoreSection extends AbstractTransposable<ScoreSection>
    implements StaveSystemContainer, MutableObject<ScoreSection> {

  private ScoreSectionName name;

  private List<ScoreRow> rows;

  private StaveSystem staveSystem;

  private Score score;

  private boolean immutable;

  /**
   * The constructor.
   */
  public ScoreSection() {

    super();
    this.rows = new ArrayList<>();
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() section name}.
   */
  public ScoreSection(ScoreSectionName name) {

    this();
    this.name = name;
  }

  private ScoreSection(ScoreSection section, MutableObjecteCopier copier) {

    super();
    this.name = section.name;
    this.staveSystem = section.staveSystem;
    this.rows = copier.copyList(this.rows);
  }

  @Override
  public ScoreSection copy(MutableObjecteCopier copier) {

    return new ScoreSection(this, copier);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  /**
   * @return the name of this section.
   */
  public ScoreSectionName getName() {

    return this.name;
  }

  /**
   * @param name the new value of {@link #getName()}.
   * @return a new {@link ScoreSection} with the given {@link #getName() name} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreSection setName(ScoreSectionName name) {

    if (this.name == name) {
      return this;
    }
    ScoreSection section = makeMutable();
    section.name = name;
    return section;
  }

  /**
   * @return the {@link List} of {@link ScoreRow}s.
   */
  public List<ScoreRow> getRows() {

    return this.rows;
  }

  /**
   * @param i the index of the requested {@link ScoreRow}.
   * @return the {@link ScoreRow} at the given index. Will be {@code null} if no row exists at the given index.
   * @see List#get(int)
   */
  public ScoreRow getRow(int i) {

    if (i >= this.rows.size()) {
      return null;
    }
    return this.rows.get(i);
  }

  /**
   * @param row the {@link ScoreRow} to add to {@link #getRows() rows}.
   */
  public void addRow(ScoreRow row) {

    this.rows.add(row);
  }

  /**
   * @return {@code true} if this section has no {@link #getRows() rows}, {@code false} otherwise.
   */
  public boolean isEmpty() {

    return this.rows.isEmpty();
  }

  @Override
  public StaveSystem getStaveSystem() {

    return this.staveSystem;
  }

  @Override
  public ScoreSection setStaveSystem(StaveSystem staveSystem) {

    if (this.staveSystem == staveSystem) {
      return this;
    }
    ScoreSection section = makeMutable();
    section.staveSystem = staveSystem;
    return section;
  }

  /**
   * @return the {@link Score} {@link Score#getSections() owning} this {@link ScoreSection}.
   */
  public Score getScore() {

    return this.score;
  }

  /**
   * @param score new value of {@link #getScore()}.
   * @return a new {@link ScoreSection} with the given {@link #getScore() score} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreSection setScore(Score score) {

    if (this.score == score) {
      return this;
    }
    ScoreSection section = makeMutable();
    section.score = score;
    return section;
  }

  @Override
  public ScoreSection makeImmutable() {

    if (!this.immutable) {
      this.rows = MutableObjecteHelper.makeImmutableRecursive(this.rows);
      this.immutable = true;
    }
    return this;
  }

  @Override
  public ScoreSection transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreSection section = new ScoreSection(this.name);
    for (ScoreRow row : this.rows) {
      section.rows.add(row.transpose(steps, diatonic, context));
    }
    return section;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.name != null) {
      this.name.toString(sb);
      sb.append('\n');
    }
    for (ScoreRow row : this.rows) {
      row.toString(sb);
    }
  }
}
