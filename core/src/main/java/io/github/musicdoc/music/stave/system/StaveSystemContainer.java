package io.github.musicdoc.music.stave.system;

/**
 * Container for {@link StaveSystem}.
 */
public interface StaveSystemContainer {

  /**
   * @return the {@link StaveSystem}.
   */
  StaveSystem getStaveSystem();

  /**
   * @param staveSystem new value of {@link #getStaveSystem()}.
   * @return {@code this} object itself for fluent API calls. May also be a copy in case of
   *         {@link io.github.musicdoc.MutableObject}.
   */
  StaveSystemContainer setStaveSystem(StaveSystem staveSystem);

}
