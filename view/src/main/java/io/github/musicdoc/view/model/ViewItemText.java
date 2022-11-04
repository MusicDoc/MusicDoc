package io.github.musicdoc.view.model;

import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.layout.ViewPlacementType;
import io.github.musicdoc.view.layout.ViewTextType;

/**
 * {@link ViewItem} that draws text or musical symbols as glyphs.
 */
public class ViewItemText extends ViewItemDrawStart {

  private final String text;

  private final ViewTextType type;

  private double scaleX;

  private double scaleY;

  /**
   * The constructor.
   *
   * @param voice the {@link #getVoice() voice}.
   * @param text the {@link #getText() text}.
   * @param type the {@link #getType() type}.
   */
  public ViewItemText(StaveVoice voice, String text, ViewTextType type) {

    super(voice);
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
   * @return the {@link ViewTextType} used to render the {@link #getText() text}. Will determine font and size.
   */
  public ViewTextType getType() {

    return this.type;
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
  public ViewPlacementType getPlacementType() {

    return this.type.asPlacementType();
  }

  @Override
  public String toString() {

    return this.text;
  }

}
