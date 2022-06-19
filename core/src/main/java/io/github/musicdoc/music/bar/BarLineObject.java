package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.partiture.voice.PartitureVoiceCell;

/**
 * Interface for {@link BarLine} and {@link BarLineType}.
 */
public interface BarLineObject {

  /**
   * @return {@code true} if this bar can only be used as {@link PartitureVoiceCell#getLeftBar()} left bar},
   *         {@code false} otherwise.
   */
  boolean isLeftBarOnly();

  /**
   * @return {@code true} if this bar can only be used as {@link StaveColumn#getRightBar() right bar}, {@code false}
   *         otherwise.
   */
  boolean isRightBarOnly();

}
