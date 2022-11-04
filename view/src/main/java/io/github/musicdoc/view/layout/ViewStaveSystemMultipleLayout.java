package io.github.musicdoc.view.layout;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.stave.system.StaveSystemMultiple;

/**
 * {@link ViewStaveSystemLayout} for {@link StaveSystemMultiple}.
 */
public class ViewStaveSystemMultipleLayout extends ViewStaveSystemLayout {

  private final List<ViewStaveSystemLayout> children;

  /**
   * The constructor.
   *
   * @param system the {@link StaveSystemMultiple}.
   */
  public ViewStaveSystemMultipleLayout(StaveSystemMultiple system) {

    super(system);
    this.children = new ArrayList<>();
  }

  @Override
  public boolean isSingle() {

    return false;
  }

  /**
   * @return the {@link List} of child {@link ViewStaveSystemLayout}s.
   */
  public List<ViewStaveSystemLayout> getChildren() {

    return this.children;
  }

}
