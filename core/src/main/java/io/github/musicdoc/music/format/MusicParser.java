package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.TextMusicInputStream;

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

    return parse(new TextMusicInputStream(string));
  }

  /**
   * @param chars the {@link MusicInputStream} to parse.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  T parse(MusicInputStream chars);

  /**
   * @param chars the {@link MusicInputStream} to parse.
   * @param options the {@link SongFormatOptions}.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  T parse(MusicInputStream chars, SongFormatOptions options);

}
