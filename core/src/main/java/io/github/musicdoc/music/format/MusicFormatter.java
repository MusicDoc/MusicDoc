package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.io.TextMusicOutputStream;

/**
 * Interface for a formatter that allows to {@link #format(Object, MusicOutputStream, SongFormatOptions) format} a
 * specific musical object to a {@link String}.
 *
 * @param <T> type of the musical object to format.
 */
public interface MusicFormatter<T> {

  /**
   * @param object the musical object to format.
   * @param out the {@link MusicOutputStream} where to {@link MusicOutputStream#append(Object) write} to.
   * @param options the {@link SongFormatOptions}.
   */
  void format(T object, MusicOutputStream out, SongFormatOptions options);

  /**
   * @param object the musical object to format.
   * @param options the {@link SongFormatOptions}.
   * @return the given {@code object} formatted as text.
   */
  default String format(T object, SongFormatOptions options) {

    StringBuilder buffer = new StringBuilder();
    TextMusicOutputStream tmos = TextMusicOutputStream.of(buffer);
    format(object, tmos, options);
    return buffer.toString();
  }

  /**
   * @param object the musical object to format.
   * @param options the {@link SongFormatOptions}.
   * @return the given {@code object} formatted as text.
   */
  String format(T object);

}
