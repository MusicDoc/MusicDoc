package io.github.musicdoc.view.model;

import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.layout.ViewPlacement;
import io.github.musicdoc.view.layout.ViewTextType;

/**
 * {@link ViewItem} that draws text or musical symbols as glyphs.
 */
public class ViewItemText extends ViewItemDrawPosition {

  private String text;

  private ViewTextType type;

  private double scaleX;

  private double scaleY;

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   */
  public ViewItemText(ViewPlacement placement) {

    super(placement);
    this.scaleX = 1;
    this.scaleY = 1;
  }

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   * @param draw the {@link #getPositionDraw() draw position}.
   * @param text the {@link #getText() text}.
   * @param type the {@link #getType() type}.
   */
  public ViewItemText(ViewPlacement placement, ViewPositionBean topLeft, ViewPositionBean bottomRight,
      ViewPositionBean draw, String text, ViewTextType type) {

    super(placement, topLeft, bottomRight, draw);
    this.text = text;
    this.type = type;
    this.scaleX = 1;
    this.scaleY = 1;
  }

  /**
   * @return the text to render.
   */
  public String getText() {

    return this.text;
  }

  /**
   * @param text new value of {@link #getText()}.
   */
  public void setText(String text) {

    this.text = text;
  }

  /**
   * @return the {@link ViewTextType} used to render the {@link #getText() text}. Will determine font and size.
   */
  public ViewTextType getType() {

    return this.type;
  }

  /**
   * @param type new value of {@link #getType()}.
   */
  public void setType(ViewTextType type) {

    this.type = type;
  }

  /**
   * @return the horizontal scale factor. Will be {@code 1} for no scale. Higher values will stretch and lower values
   *         will shrink horizontally.
   */
  public double getScaleX() {

    return this.scaleX;
  }

  /**
   * @param scaleX new value of {@link #getScaleX()}.
   */
  public void setScaleX(double scaleX) {

    this.scaleX = scaleX;
  }

  /**
   * @return the vertical scale factor. Will be {@code 1} for no scale. Higher values will stretch and lower values will
   *         shrink vertically.
   */
  public double getScaleY() {

    return this.scaleY;
  }

  /**
   * @param scaleY new value of {@link #getScaleY()}.
   */
  public void setScaleY(double scaleY) {

    this.scaleY = scaleY;
  }

  @Override
  public String toString() {

    return this.text;
  }

}
