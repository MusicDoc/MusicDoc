package io.github.musicdoc.music.score.section;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreRow;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents a logical section of a {@link Score}. Each section has a {@link #getName() name} such as "Verse 1" and
 * multiple {@link #getRows() rows}.
 */
public class ScoreSection extends AbstractTransposable<ScoreSection> {

  private ScoreSectionName name;

  private final List<ScoreRow> rows;

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

  /**
   * @return the name of this section.
   */
  public ScoreSectionName getName() {

    return this.name;
  }

  /**
   * @param name the new value of {@link #getName()}.
   */
  public void setName(ScoreSectionName name) {

    this.name = name;
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
  public ScoreSection transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreSection section = new ScoreSection(this.name);
    for (ScoreRow row : this.rows) {
      section.rows.add(row.transpose(steps, diatonic, context));
    }
    return section;
  }

  @Override
  public String toString() {

    if (this.name == null) {
      return super.toString();
    }
    return this.name.toString();
  }
}
