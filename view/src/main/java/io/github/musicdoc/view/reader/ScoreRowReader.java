package io.github.musicdoc.view.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.rhythm.metre.BeatPosition;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.stave.activity.StaveAcitvity;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.ViewContext;

/**
 * Reader for {@link ScoreRow} with status pointing to reading-position(s).
 */
public class ScoreRowReader {

  private final List<ScoreLineReader> lines;

  /**
   * The constructor.
   *
   * @param row the {@link ScoreRow} to wrap.
   * @param position the {@link BeatPosition}.
   * @param context the {@link ViewContext}.
   */
  public ScoreRowReader(ScoreRow row, BeatPosition position, ViewContext context) {

    super();
    Objects.requireNonNull(row);
    this.lines = new ArrayList<>(row.getLines().size());
    StaveAcitvity staveAcitvity = context.getStaveAcitvity();
    for (ScoreLine line : row.getLines()) {
      if (line.getCellCount() > 0) {
        StaveVoice voice = line.getVoice();
        boolean active = staveAcitvity.isActive(voice);
        if (active) {
          this.lines.add(new ScoreLineReader(line, position));
        }
      }
    }
  }

  /**
   * @return the number of {@link #getLine(int) lines} available.
   */
  public int getLineCount() {

    return this.lines.size();
  }

  /**
   * @param lineIndex the index of the requested {@link ScoreLineReader}. Should be in the range from {@code 0} to
   *        <code>{@link #getLineCount() lineCount}-1</code>.
   * @return the {@link ScoreLineReader} at the given {@code lineIndex} or {@code null} if no such line exists.
   */
  public ScoreLineReader getLine(int lineIndex) {

    if ((lineIndex < 0) || (lineIndex >= this.lines.size())) {
      return null;
    }
    return this.lines.get(lineIndex);
  }

  /**
   * @return {@code true} if all {@link ScoreLineReader}s are {@link ScoreLineReader#isDone() done} and this row is
   *         entirely consumed, {@code false} otherwise.
   */
  public boolean isDone() {

    for (ScoreLineReader line : this.lines) {
      if (!line.isDone()) {
        return false;
      }
    }
    return true;
  }

}
