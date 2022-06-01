package io.github.musicdoc.music.bar;

/**
 * Interface for {@link Bar} and {@link BarType}.
 */
public interface BarObject {

  /**
   * @return {@code true} if this bar can only be used as {@link StaveColumn#getLeftBar()} left bar}, {@code false}
   *         otherwise.
   */
  boolean isLeftBarOnly();

  /**
   * @return {@code true} if this bar can only be used as {@link StaveColumn#getRightBar() right bar}, {@code false}
   *         otherwise.
   */
  boolean isRightBarOnly();

}
