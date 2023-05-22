package io.github.musicdoc.view.model;

import io.github.musicdoc.view.layout.ViewPlacementType;

/**
 * {@link ViewItem} for beams that connect the stems for groups of {@link io.github.musicdoc.note.Note}s.
 */
public class ViewItemBeam extends ViewItem {

  private int beamCount;

  private boolean up;

  /**
   * The constructor.
   */
  public ViewItemBeam() {

    super();
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
   * @return {@code true} if the beam is going upwards from {@link #getX0() x0},{@link #getY1() y1} to {@link #getX1()
   *         x1},{@link #getY0() y0}, {@code false} otherwise (downwards from {@link #getTopLeft() top left position}
   *         ({@link #getX0() x0},{@link #getY0() y0}) to {@link #getBottomRight() bottom right position}
   *         ({@link #getX1() x1},{@link #getY1() y1})).
   */
  public boolean isUp() {

    return this.up;
  }

  /**
   * @param down new value of {@link #isUp()}.
   */
  public void setUp(boolean down) {

    this.up = down;
  }

  @Override
  public ViewPlacementType getPlacementType() {

    return null;
  }

}
