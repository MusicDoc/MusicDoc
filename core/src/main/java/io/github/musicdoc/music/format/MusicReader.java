package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicInputStream;

/**
 * Interface for a reader that can parse an object from a {@link MusicInputStream}.
 *
 * @param <T> type of the object to parse.
 */
public interface MusicReader<T> {

  /**
   * @param string the {@link String} containing the object to parse ({@code <T>}} in is {@link #toString() string
   *        representation}.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   * @see MusicWriter#write(Object)
   */
  T read(String string);

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  T read(MusicInputStream in);

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the parsed object or {@code null} if no valid syntax was found for the requested object.
   */
  T read(MusicInputStream in, SongFormatContext context);

}
