package io.github.musicdoc.view.layout;

import java.util.Objects;

import io.github.musicdoc.view.data.ViewPositionType;
import io.github.musicdoc.view.model.ViewItem;

/**
 * Enum with the available vertical placements of {@link io.github.musicdoc.view.model.ViewItem}s within a
 * {@link io.github.musicdoc.stave.Stave} having their own horizontal row alignment.
 */
public class ViewPlacement {

  /** {@link ViewPlacement} for absolute {@link ViewItem}s such as headers, footers, or stave brackets. */
  public static final ViewPlacement ABSOLUTE = new ViewPlacement();

  private final int index;

  private final ViewPlacementType type;

  private ViewPlacement() {

    super();
    this.index = -1;
    this.type = null;
  }

  /**
   * The constructor.
   *
   * @param index the {@link #getIndex() stave index}.
   * @param type the {@link ViewPositionType}.
   */
  public ViewPlacement(int index, ViewPlacementType type) {

    super();
    assert (index >= 0);
    Objects.requireNonNull(type);
    this.index = index;
    this.type = type;
  }

  /**
   * @return the index number of the {@link io.github.musicdoc.stave.Stave} in the
   *         {@link io.github.musicdoc.stave.system.StaveSystem}.
   */
  public int getIndex() {

    return this.index;
  }

  /**
   * @return the {@link ViewPositionType}.
   */
  public ViewPlacementType getType() {

    return this.type;
  }

  @Override
  public int hashCode() {

    return Objects.hash(Integer.valueOf(this.index), this.type);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ViewPlacement other = (ViewPlacement) obj;
    return this.index == other.index && Objects.equals(this.type, other.type);
  }

}
