package io.github.musicdoc.io;

/**
 * A {@link MusicStream} to write data to.
 */
public interface MusicOutputStream extends MusicStream {

  /**
   * @param value the value to append its {@link Object#toString() string representation} to this stream.
   * @see StringBuilder#append(Object)
   */
  void write(Object value);

  /**
   * @param c the character to append to this stream.
   * @see StringBuilder#append(char)
   */
  void write(char c);

  /**
   * @param propertyName the name of the property to start.
   */
  void writePropertyStart(String propertyName);

  /**
   * @param propertyName the name of the property to end.
   */
  void writePropertyEnd(String propertyName);

  /**
   * @param propertyName the name of the property to write.
   * @param propertyValue the value of the property to write.
   */
  default void writeProperty(String propertyName, String propertyValue) {

    writePropertyStart(propertyName);
    write(propertyValue);
    writePropertyEnd(propertyName);
  }

}
