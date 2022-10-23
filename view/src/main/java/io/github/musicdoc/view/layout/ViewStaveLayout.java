package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.data.ViewSizeBean;

/**
 * Layout state for the left side of the {@link io.github.musicdoc.stave.system.StaveSystem}.
 */
public class ViewStaveLayout extends ViewSizeBean {

  private ViewStaveLayout next;

  /**
   * @return the next {@link ViewStaveLayout} or {@code null} if this is the last one.
   */
  public ViewStaveLayout getNext() {

    return this.next;
  }

  /**
   * @return the next {@link ViewStaveLayout}. Will be created if not yet existing.
   */
  public ViewStaveLayout getOrCreateNext() {

    if (this.next == null) {
      this.next = new ViewStaveLayout();
    }
    return this.next;
  }

}
