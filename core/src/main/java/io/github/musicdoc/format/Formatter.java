package io.github.musicdoc.format;

import java.io.IOException;

/**
 * Interface for a formatter that allows to {@link #format(Object, Appendable, SongFormatOptions) format} a specific
 * musical object to a {@link String}.
 *
 * @param <T> type of the musical object to format.
 */
public interface Formatter<T> {

  /**
   * @param object the object to format.
   * @param buffer the {@link Appendable} (e.g. {@link StringBuilder}) where to {@link Appendable#append(CharSequence)
   *        append} the formatted text.
   * @param options the {@link SongFormatOptions}.
   * @throws IOException on IO error.
   */
  void format(T object, Appendable buffer, SongFormatOptions options) throws IOException;

}
