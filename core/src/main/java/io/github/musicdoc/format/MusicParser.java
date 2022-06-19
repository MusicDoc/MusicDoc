package io.github.musicdoc.format;

import io.github.musicdoc.parser.CharStream;
import io.github.musicdoc.parser.StringCharStream;

/**
 * Interface for a parser that can rebuild an object from a {@link #toString() string representation}.
 *
 * @param <T> type of the object to parse.
 */
public interface MusicParser<T> {

  /**
   * @param string the {@link String} containing the object to parse ({@code <T>}} in is {@link #toString() string
   *        representation}.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  default T parse(String string) {

    return parse(new StringCharStream(string));
  }

  /**
   * @param chars the {@link CharStream} to parse.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  T parse(CharStream chars);

}
