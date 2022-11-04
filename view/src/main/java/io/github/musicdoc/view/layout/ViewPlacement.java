package io.github.musicdoc.view.layout;

import java.util.Objects;

import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.data.ViewPositionType;
import io.github.musicdoc.view.model.ViewItem;

/**
 * Enum with the available vertical placements of {@link io.github.musicdoc.view.model.ViewItem}s within a
 * {@link io.github.musicdoc.stave.Stave} having their own horizontal row alignment.
 */
public class ViewPlacement {

  /** {@link ViewPlacement} for absolute {@link ViewItem}s such as headers, footers, or stave brackets. */
  public static final ViewPlacement ABSOLUTE = new ViewPlacement();

  private final StaveVoice voice;

  private final ViewPlacementType type;

  private ViewPlacement() {

    super();
    this.voice = null;
    this.type = null;
  }

  /**
   * The constructor.
   *
   * @param voice the {@link #getVoice() voice}.
   * @param type the {@link ViewPositionType}.
   */
  public ViewPlacement(StaveVoice voice, ViewPlacementType type) {

    super();
    Objects.requireNonNull(voice);
    Objects.requireNonNull(type);
    this.voice = voice;
    this.type = type;
  }

  /**
   * @return the {@link StaveVoice} of this placement. Will be {@code null} only in case of {@link #ABSOLUTE}.
   */
  public StaveVoice getVoice() {

    return this.voice;
  }

  /**
   * @return the {@link Stave} of this placement. Will be {@code null} in case of {@link #ABSOLUTE}.
   */
  public Stave getStave() {

    if (this.voice == null) {
      return null;
    }
    return this.voice.getStave();
  }

  /**
   * @return the {@link ViewPositionType}. Will be {@code null} only in case of {@link #ABSOLUTE}.
   */
  public ViewPlacementType getType() {

    return this.type;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.voice, this.type);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ViewPlacement other = (ViewPlacement) obj;
    return Objects.equals(this.voice, other.voice) && Objects.equals(this.type, other.type);
  }

}
