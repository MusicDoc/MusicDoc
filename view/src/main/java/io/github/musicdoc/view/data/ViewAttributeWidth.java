package io.github.musicdoc.view.data;

import io.github.musicdoc.view.model.ViewObject;

/**
 * {@link ViewObject} that has a {@link #getWidth() width}.
 */
public interface ViewAttributeWidth extends ViewObject {

  /**
   * @return the width of this object.
   */
  double getWidth();

}
