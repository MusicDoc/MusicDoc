package io.github.musicdoc.view.reader;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.rhythm.metre.BeatPosition;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.layout.ViewBarLineLayout;
import io.github.musicdoc.view.render.ViewTextRenderer;

/**
 * Reader for {@link ScoreLine} with status pointing to reading-position.
 */
public class ScoreLineReader {

  private final ScoreLine line;

  private final BeatPosition position;

  private int cellIndex;

  private ViewBarLineLayout barLineLayout;

  private boolean done;

  /**
   * The constructor.
   *
   * @param line the {@link ScoreLine} to read.
   * @param position the optional existing {@link BeatPosition}.
   */
  public ScoreLineReader(ScoreLine line, BeatPosition position) {

    super();
    this.line = line;
    this.position = new BeatPosition(position);
  }

  /**
   * @return the {@link BeatPosition}.
   */
  public BeatPosition getPosition() {

    return this.position;
  }

  /**
   * @return the current {@link ScoreCell} or {@code null} if the end of this line has been reached.
   */
  public ScoreCell getCell() {

    return this.line.getCell(this.cellIndex);
  }

  /**
   * Gets and consumes the {@link #getCell() current cell}. So if a non-{@code null} {@link ScoreCell} is returned, this
   * method will also increment the cell index and {@link #getPosition() position}.
   *
   * @return the {@link #getCell() current cell} or {@code null} if the end of this line has been reached.
   */
  public ScoreCell getNext() {

    ScoreCell cell = this.line.getCell(this.cellIndex);
    if (cell == null) {
      this.done = true;
    } else {
      this.cellIndex++;
      this.position.add(cell);
      BarLine bar = cell.getBar();
      if (bar != null) {
        // TODO
        StaveVoice voice = null;
        ViewTextRenderer textRenderer = null;
        this.barLineLayout = ViewBarLineLayout.of(bar, this.position.getBar(), voice, textRenderer);
      }
    }
    return cell;

  }

  /**
   * @return the {@link ScoreLine} the view is rendered from.
   */
  public ScoreLine getLine() {

    return this.line;
  }

  /**
   * @return {@code true} if done and all {@link ScoreCell}s are {@link #getNext() consumed}, {@code false} otherwise.
   */
  public boolean isDone() {

    return this.done;
  }

}
