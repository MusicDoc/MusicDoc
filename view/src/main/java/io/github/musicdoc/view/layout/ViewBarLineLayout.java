package io.github.musicdoc.view.layout;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.model.ViewItemText;
import io.github.musicdoc.view.model.ViewItemVolta;
import io.github.musicdoc.view.render.ViewTextRenderer;
import io.github.musicdoc.volta.Volta;

/**
 * TODO hohwille This type ...
 *
 */
public class ViewBarLineLayout {

  private final BarLine barLine;

  private final ViewItemText itemBar;

  private final ViewItemVolta itemVolta;

  private final int start;

  private final int end;

  private ViewBarLineLayout(BarLine barLine, ViewItemText itemBar, int start, int end, ViewItemVolta itemVolta) {

    super();
    this.barLine = barLine;
    this.itemBar = itemBar;
    this.start = start;
    this.end = end;
    this.itemVolta = itemVolta;
  }

  /**
   * @return the {@link BarLine} this layout is about.
   */
  public BarLine getBarLine() {

    return this.barLine;
  }

  /**
   * @return the {@link ViewItemText} representing the actual {@link BarLine#getType() bar}.
   */
  public ViewItemText getItemBar() {

    return this.itemBar;
  }

  /**
   * @return the optional {@link ViewItemVolta}. Will be {@code null} if {@link #getBarLine() barLine} does not
   *         {@link BarLine#getVolta() have} a {@link Volta}.
   */
  public ViewItemVolta getItemVolta() {

    return this.itemVolta;
  }

  /**
   * @return the {@link io.github.musicdoc.rhythm.metre.BeatPosition#getBar() bar number} where the bar starts.
   */
  public int getStart() {

    return this.start;
  }

  /**
   * @return the {@link io.github.musicdoc.rhythm.metre.BeatPosition#getBar() bar number} where the bar ends.
   */
  public int getEnd() {

    return this.end;
  }

  public static ViewBarLineLayout of(BarLine barLine, int start, StaveVoice voice, ViewTextRenderer textRenderer) {

    int end = start;
    ViewItemText itemBar = textRenderer.renderText(barLine.getType());
    ViewItemVolta itemVolta = null;
    Volta volta = barLine.getVolta();
    if (volta != null) {
      end = start + 1 + volta.getExtraBars();
      itemVolta = new ViewItemVolta(voice, volta); // TODO
    }
    return new ViewBarLineLayout(barLine, itemBar, start, end, itemVolta);
  }

}
