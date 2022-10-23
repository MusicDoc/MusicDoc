package io.github.musicdoc.view.data;

import io.github.musicdoc.view.model.ViewObject;

/**
 * {@link ViewObject} that has a {@link #getHeight() height}.
 */
public interface ViewAttributeHeight extends ViewObject {

  /**
   * @return the height of this object.
   */
  double getHeight();

}
