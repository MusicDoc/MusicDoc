package io.github.musicdoc.view.layout;

import io.github.musicdoc.view.model.ViewItemText;

/**
 * Abstract base class for a layout of a {@link io.github.musicdoc.stave.system.StaveSystem},
 * {@link io.github.musicdoc.stave.Stave} or {@link io.github.musicdoc.stave.voice.StaveVoice}.
 *
 * @see ViewStaveSystemLayout
 * @see ViewStaveVoiceLayout
 */
public abstract class ViewStaveObjectLayout {

  ViewItemText label;

  /**
   * @return the {@link ViewItemText} for the label ({@link io.github.musicdoc.AbstractMutableLabeledObject#getTitle()
   *         name} or {@link io.github.musicdoc.AbstractMutableLabeledObject#getAbbreviation() abbreviation}) or
   *         {@code null} for none.
   */
  public ViewItemText getLabel() {

    return this.label;
  }

  /**
   * @param label new value of {@link #getLabel()}.
   */
  public void setLabel(ViewItemText label) {

    this.label = label;
  }

}
