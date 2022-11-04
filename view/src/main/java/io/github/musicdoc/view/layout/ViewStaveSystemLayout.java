package io.github.musicdoc.view.layout;

import java.util.List;

import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.view.model.ViewItemText;

/**
 * Abstract base-class for a layout of a {@link StaveSystem}.
 */
public abstract class ViewStaveSystemLayout extends ViewStaveObjectLayout {

  private final StaveSystem system;

  ViewItemText bracket;

  /**
   * The constructor.
   *
   * @param system the {@link StaveSystem}.
   */
  public ViewStaveSystemLayout(StaveSystem system) {

    super();
    this.system = system;
  }

  /**
   * @return the {@link StaveSystem} this layout is about.
   */
  public StaveSystem getSystem() {

    return this.system;
  }

  /**
   * @return the {@link ViewItemText} for the {@link StaveSystem#getBracket() bracket} or {@code null} for none (if
   *         bracket is {@code null}).
   */
  public ViewItemText getBracket() {

    return this.bracket;
  }

  /**
   * @param bracket new value of {@link #getBracket()}.
   */
  public void setBracket(ViewItemText bracket) {

    this.bracket = bracket;
  }

  /**
   * @return {@code true} if single {@link ViewStaveSystemSingleLayout}, {@code false} otherwise (if multiple
   *         {@link ViewStaveSystemMultipleLayout}).
   * @see StaveSystem#isSingle()
   */
  public abstract boolean isSingle();

  /**
   * @return the {@link List} of child {@link ViewStaveSystemLayout}s.
   */
  public abstract List<ViewStaveSystemLayout> getChildren();
}
