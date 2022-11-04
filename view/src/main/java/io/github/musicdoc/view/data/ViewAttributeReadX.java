package io.github.musicdoc.view.data;

import io.github.musicdoc.view.model.ViewObject;

/**
 * Interface to read {@link #getX() x-coordinate}.
 */
public interface ViewAttributeReadX extends ViewObject {

  /**
   * @return the x-coordinate on the horizontal axis with {@code 0} as the left of the page or screen.
   */
  double getX();

}
