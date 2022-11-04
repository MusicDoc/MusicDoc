package io.github.musicdoc.view.model;

import java.util.Objects;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.data.ViewRectangle;
import io.github.musicdoc.view.data.ViewRectangleBean;
import io.github.musicdoc.view.layout.ViewPlacementType;

/**
 * A single item that can be drawn. Defines a {@link ViewRectangle rectangle} with the bounding box of its content on
 * the page or screen.
 */
public abstract class ViewItem extends ViewRectangleBean {

  private final StaveVoice voice;

  private final Stave stave;

  /**
   * The constructor.
   *
   * @param voice the {@link #getVoice() voice}.
   */
  public ViewItem(StaveVoice voice) {

    super();
    this.voice = voice;
    if (voice == null) {
      this.stave = null;
    } else {
      this.stave = voice.getStave();
    }

  }

  /**
   * The constructor.
   *
   * @param stave the {@link #getStave() stave}.
   */
  public ViewItem(Stave stave) {

    super();
    Objects.requireNonNull(stave);
    this.voice = null;
    this.stave = stave;
  }

  /**
   * The constructor for an absolute item that does not {@link #getStave() have a stave associated}.
   */
  public ViewItem() {

    super();
    this.voice = null;
    this.stave = null;
  }

  /**
   * @return the {@link ViewPositionBean position} where to start drawing. Typically {@link #getTopLeft() top left
   *         position} but may also be a position within the rectangle, e.g. to text that is baseline aligned and may
   *         have a tilt or in case of an ellipse the center of the rectangle.
   */
  public ViewPositionBean getDrawStart() {

    return getTopLeft();
  }

  /**
   * @return the {@link ViewPositionBean position} where to end drawing. Typically {@link #getBottomRight() bottom right
   *         position} but may also be a position within the rectangle, e.g. to draw a tie, slur or beams from
   *         {@link #getDrawStart() draw start} to {@link #getDrawEnd() draw end}.
   */
  public ViewPositionBean getDrawEnd() {

    return getBottomRight();
  }

  /**
   * @return the {@link StaveVoice} this item belongs to. May be {@code null} for absolute positioned items like headers
   *         or footers as well as for {@link ViewItemStave}.
   */
  public StaveVoice getVoice() {

    return this.voice;
  }

  /**
   * @return the {@link Stave} this item belongs to.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @return the {@link ViewPlacementType} of this item or {@code null} if not aligned with a {@link Stave}.
   */
  public abstract ViewPlacementType getPlacementType();

}
