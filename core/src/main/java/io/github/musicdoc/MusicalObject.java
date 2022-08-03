package io.github.musicdoc;

/**
 * Marker-Interface for all major objects related to music.
 */
public interface MusicalObject {

  /**
   * Variant of {@link Object#toString()} that allows implementation for complex hierarchical more efficiently.
   *
   * @param sb the {@link StringBuilder} where to append the {@link Object#toString() string representation}.
   */
  void toString(StringBuilder sb);
}
