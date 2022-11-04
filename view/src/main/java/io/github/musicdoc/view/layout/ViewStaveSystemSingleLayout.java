package io.github.musicdoc.view.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.system.StaveSystemSingle;
import io.github.musicdoc.view.model.ViewItemStave;

/**
 * {@link ViewStaveSystemLayout} for {@link StaveSystemSingle}.
 */
public class ViewStaveSystemSingleLayout extends ViewStaveSystemLayout {

  private final Stave stave;

  private final List<ViewStaveVoiceLayout> voices;

  private ViewItemStave viewStave;

  /**
   * The constructor.
   *
   * @param system the {@link StaveSystemSingle}.
   */
  public ViewStaveSystemSingleLayout(StaveSystemSingle system) {

    super(system);
    this.stave = system.getStave();
    this.voices = new ArrayList<>();
  }

  @Override
  public boolean isSingle() {

    return true;
  }

  /**
   * @return the {@link Stave} this layout is about.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @return the {@link List} of {@link ViewStaveVoiceLayout}s.
   */
  public List<ViewStaveVoiceLayout> getVoices() {

    return this.voices;
  }

  @Override
  public List<ViewStaveSystemLayout> getChildren() {

    return Collections.emptyList();
  }

  /**
   * @return the {@link ViewItemStave}.
   */
  public ViewItemStave getViewStave() {

    return this.viewStave;
  }

}
