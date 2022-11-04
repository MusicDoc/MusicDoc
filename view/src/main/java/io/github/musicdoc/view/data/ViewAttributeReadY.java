package io.github.musicdoc.view.data;

import io.github.musicdoc.view.model.ViewObject;

/**
 * Interface to read {@link #getY() y-coordinate}.
 */
public interface ViewAttributeReadY extends ViewObject {

  /**
   * @return the y-coordinate on the vertical axis with {@code 0} as the top of the page or screen.
   */
  double getY();

}
