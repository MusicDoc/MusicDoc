package io.github.musicdoc.view;

import java.util.Locale;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.activity.StaveAcitvity;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.render.ViewRenderer;

/**
 * Context for {@link ViewRenderer rendering}.
 */
public class ViewContext {

  private Locale locale;

  private double scale;

  private StaveAcitvity staveAcitvity;

  /**
   * @return the {@link Locale} for l10n.
   */
  public Locale getLocale() {

    if (this.locale == null) {
      this.locale = Locale.getDefault();
    }
    return this.locale;
  }

  /**
   * @param locale new value of {@link #getLocale()}.
   */
  public void setLocale(Locale locale) {

    this.locale = locale;
  }

  /**
   * @return the scale or zoom factor. Use {@code 1.0} for normal size. Smaller values will zoom out and bigger values
   *         will zoom in. E.g. {@code 0.5} will zoom out to half (50%) of normal size and {@code 2.0} will zoom in to
   *         double (200%) of normal size.
   */
  public double getScale() {

    return this.scale;
  }

  /**
   * @param scale new value of {@link #getScale()}. Must be in the range from {@code 0.1} to {@code 10.0}
   */
  public void setScale(double scale) {

    if ((scale < 0.1) || (scale > 10.0)) {
      throw new IllegalArgumentException("" + scale);
    }
    this.scale = scale;
  }

  /**
   * @return the {@link StaveAcitvity} used to determine which {@link Stave}s and {@link StaveVoice}s are active and
   *         shall be rendered, while all others are inactive and will be hidden.
   */
  public StaveAcitvity getStaveAcitvity() {

    if (this.staveAcitvity == null) {
      return StaveAcitvity.ACTIVATE_ALL;
    }
    return this.staveAcitvity;
  }

  /**
   * @param staveAcitvity new value of {@link #getStaveAcitvity()}.
   */
  public void setStaveAcitvity(StaveAcitvity staveAcitvity) {

    this.staveAcitvity = staveAcitvity;
  }

}
