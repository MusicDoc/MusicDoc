package io.github.musicdoc.music.score;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Represents the entire score of a {@link io.github.musicdoc.music.song.Song} with potential lyrics, chords, scales,
 * tabs, etc. in a structured way. It contains out of multiple {@link #getSections() sections}.
 *
 * @see io.github.musicdoc.music.song.Song#score
 */
public class Score extends AbstractTransposable<Score> {

  private final List<ScoreSection> sections;

  /**
   * The constructor.
   */
  public Score() {

    super();
    this.sections = new ArrayList<>();
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
}
