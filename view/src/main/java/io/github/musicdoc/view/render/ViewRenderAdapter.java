package io.github.musicdoc.view.render;

import io.github.musicdoc.glyphs.smufl.SmuflGlyphsStaves;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.layout.ViewTextType;
import io.github.musicdoc.view.model.ViewItemArc;

/**
 * Adapter providing functionality of the underlying graphics system. Allows to abstract from the concrete rendering
 * technology but reuse the layout engine via {@link ViewRenderer}[Impl] across different rendering or UI technologies.
 *
 * Therefore to implement your own rendering technology, all you need to do is implement this interface and create your
 * instance of {@link DefaultViewRenderer} from it. Then you can compute the desired layout as view object and simply
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

  /**
   * @param context the {@link ViewContext}.
   * @return the offset to add to the {@link io.github.musicdoc.view.data.ViewAttributeReadY#getY() y-coordinate} to
   *         shift a note glyph downwards one semitone within the stave.
   */
  default double getStepOffset(ViewContext context) {

    ViewRectangle bounds = getTextSize(SmuflGlyphsStaves.STAFF_5, ViewTextType.MUSIC_CONTENT, context);
    double height = bounds.getHeight();
    return height / 8; // 5 stave lines have 4 spaces between the lines and a semitone step shifts half of it (2*4)
  }

  /**
   * @param steps the number of chromatic steps relative to the base tone in the stave.
   * @param context the {@link ViewContext}.
   * @return the offset to add to the {@link io.github.musicdoc.view.data.ViewAttributeReadY#getY() y-coordinate} to
   *         place the note at the proper vertical position in the stave according to the given {@code steps}. Will be
   *         positive if the {@code steps} are positive as the tone is lower than the base tone in the stave and will be
   *         negative if the {@code steps} are negative as the tone is higher.
   */
  default double getStepOffset(int steps, ViewContext context) {

    if (steps == 0) {
      return 0;
    }
    return getStepOffset(context) * steps;
  }

  /**
   * @param arc the {@link ViewItemArc} to adjust in its height so that the arc does not exceed its bounding box.
   * @param context the {@link ViewContext}.
   */
  default void adjustHeight(ViewItemArc arc, ViewContext context) {

    double scale = context.getScale();
    // TODO should depend on delta-y (draw start y - draw end y)
    double yOffset = 10 * scale;
    if (arc.isUp()) {
      arc.getTopLeft().addY(-yOffset);
    } else {
      arc.getBottomRight().addY(yOffset);
    }
  }

}
