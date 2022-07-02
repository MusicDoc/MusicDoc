package io.github.musicdoc.io;

/**
 * A {@link MusicStream} to write data to.
 */
public interface MusicOutputStream extends MusicStream {

  /**
   * @param value the value to append its {@link Object#toString() string representation} to this stream.
   * @see StringBuilder#append(Object)
   */
  void append(Object value);

  /**
   * @param propertyName the name of the property to start.
   */
  void startProperty(String propertyName);

  /**
   * @param propertyName the name of the property to end.
   */
  void endProperty(String propertyName);

  /**
   * @param propertyName the name of the property to write.
   * @param propertyValue the value of the property to write.
   */
  default void writeProperty(String propertyName, String propertyValue) {

    startProperty(propertyName);
    append(propertyValue);
    endProperty(propertyName);
  }

}
