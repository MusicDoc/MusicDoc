package io.github.musicdoc.view.model;

import io.github.musicdoc.view.data.ViewPositionBean;
import io.github.musicdoc.view.layout.ViewPlacement;

/**
 * {@link ViewItem} for beams that connect the stems for groups of {@link io.github.musicdoc.note.Note}s.
 */
public class ViewItemBeam extends ViewItem {

  private int beamCount;

  private boolean down;

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   * @param topLeft the {@link #getPositionTopLeft() top left position}.
   * @param bottomRight the {@link #getPositionBottomRight() bottom right position}.
   */
  public ViewItemBeam(ViewPlacement placement, ViewPositionBean topLeft, ViewPositionBean bottomRight) {

    super(placement, topLeft, bottomRight);
  }

  /**
   * The constructor.
   *
   * @param placement the {@link #getPlacement() placement}.
   */
  public ViewItemBeam(ViewPlacement placement) {

    super(placement);
  }

  /**
   * @return the number of beams. Will be {@code 1} for {@link io.github.musicdoc.rhythm.value.MusicalValue#_1_4
   *         fourth}, {@code 2} for {@link io.github.musicdoc.rhythm.value.MusicalValue#_1_8 eighth}, {@code 3} for
   *         {@link io.github.musicdoc.rhythm.value.MusicalValue#_1_16 sixteenth}, and {@code 4} for
   *         {@link io.github.musicdoc.rhythm.value.MusicalValue#_1_32 thirty-second}
   *         {@link io.github.musicdoc.note.Note}.
   */
  public int getBeamCount() {

    return this.beamCount;
  }

  /**
   * @param beamCount new value of {@link #getBeamCount()}.
   */
  public void setBeamCount(int beamCount) {

    this.beamCount = beamCount;
  }

  /**
   * @return {@code true} if the beam is going downwards from {@link #getPositionTopLeft() top left position}
   *         ({@link #getX0() x0},{@link #getY0() y0}) to {@link #getPositionBottomRight() bottom right position}
   *         ({@link #getX1() x1},{@link #getY1() y1}), {@code false} otherwise (upwards from {@link #getX0()
   *         x0},{@link #getY1() y1} to {@link #getX1() x1},{@link #getY0() y0}).
   */
  public boolean isDown() {

    return this.down;
  }

  /**
   * @param down new value of {@link #isDown()}.
   */
  public void setDown(boolean down) {

    this.down = down;
  }

}
