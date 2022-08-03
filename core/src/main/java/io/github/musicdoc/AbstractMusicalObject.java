package io.github.musicdoc;

/**
 * Abstract base implementation of {@link MusicalObject}.
 */
public abstract class AbstractMusicalObject implements MusicalObject {

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    toString(sb);
    return sb.toString();
  }

}
