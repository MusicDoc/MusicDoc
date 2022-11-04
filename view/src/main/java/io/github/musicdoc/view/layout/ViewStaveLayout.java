package io.github.musicdoc.view.layout;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.stave.Stave;

/**
 * Layout for a single stave line.
 */
public class ViewStaveLayout {

  private final Map<ViewPlacementType, ViewPlacementHeight> map;

  /**
   * The constructor.
   */
  public ViewStaveLayout() {

    super();
    this.map = new HashMap<>();
  }

  /**
   * @param type the {@link ViewPlacementType}.
   * @return the {@link ViewPlacementHeight} for the given {@link ViewPlacementType} or {@code null} if none available.
   */
  public ViewPlacementHeight getHeight(ViewPlacementType type) {

    return this.map.get(type);
  }

  /**
   * @param type the {@link ViewPlacementType}.
   * @return the {@link ViewPlacementHeight} for the given {@link ViewPlacementType} or {@code null} if none available.
   */
  public ViewPlacementHeight getOrCreateHeight(ViewPlacementType type) {

    return this.map.computeIfAbsent(type, (p) -> new ViewPlacementHeight());
  }

  /**
   * @return the total height as sum of all height values.
   */
  public double getTotalHeight() {

    return getTotalHeight(0);
  }

  /**
   * @param space the height to add as space between each row.
   * @return the total height as sum of all height values including the given {@code space} between each of them.
   */
  public double getTotalHeight(double space) {

    double total = 0;
    for (ViewPlacementHeight placementHeight : this.map.values()) {
      double height = placementHeight.getLength();
      if (height > 0) {
        if (total == 0) {
          total = height;
        } else {
          total = total + space + height;
        }
      }
    }
    return total;
  }

  /**
   * @param yStart the {@link io.github.musicdoc.view.data.ViewAttributeReadY#getY() y-coordinate} where to start this
   *        {@link Stave}.
   * @param space the vertical space to add between elements with different {@link ViewPlacementType} within the
   *        {@link Stave} (chord symbol, notes, lyrics, etc.).
   * @return the new {@link io.github.musicdoc.view.data.ViewAttributeReadY#getY() y-coordinate} at the bottom of the
   *         {@link Stave}.
   */
  public double layout(double yStart, double space) {

    double y = yStart;
    for (ViewPlacementType type : ViewPlacementType.values()) {
      ViewPlacementHeight placementHeight = this.map.get(type);
      if (placementHeight != null) {
        placementHeight.setYAbsolute(y);
        double height = placementHeight.getLength();
        if (height > 0) {
          y = y + height + space;
        }
      }
    }
    return y;
  }
}
