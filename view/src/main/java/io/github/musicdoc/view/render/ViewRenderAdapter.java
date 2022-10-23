package io.github.musicdoc.view.render;

import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.layout.ViewTextType;

/**
 * Adapter providing functionality of the underlying graphics system. Allows to abstract from the concrete rendering
 * technology but reuse the layout engine via {@link ViewRenderer}[Impl] across different rendering or UI technologies.
 *
 * Therefore to implement your own rendering technology, all you need to do is implement this interface and create your
 * instance of {@link ViewRendererImpl} from it. Then you can compute the desired layout as view object and simply
 * render it to your screen, PDF, SVG, or whatever output you prefer.
 */
public interface ViewRenderAdapter {

  /**
   * @param text the text to get the size of.
   * @param type the {@link ViewTextType} for choosing the font and size.
   * @param context the {@link ViewContext}.
   * @return the {@link ViewRectangle} the of text when rendered. The position part may be different from zero due to
   *         tilt and baseline.
   */
  ViewRectangle getTextSize(String text, ViewTextType type, ViewContext context);

  /**
   * @return the width of the page or screen.
   */
  double getPageWidth();

  /**
   * @return the height of the page or screen.
   */
  double getPageHeight();

}
