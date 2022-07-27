package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicOutputStream;

/**
 * Interface for a formatter that allows to {@link #write(Object, MusicOutputStream, SongFormatContext) format} a
 * specific musical object to a {@link MusicOutputStream}.
 *
 * @param <T> type of the musical object to format.
 */
public interface MusicWriter<T> {

  /**
   * @param object the musical object to format.
   * @param out the {@link MusicOutputStream} where to {@link MusicOutputStream#append(Object) write} to.
   * @param context the {@link SongFormatContext}.
   */
  void write(T object, MusicOutputStream out, SongFormatContext context);

  /**
   * @param object the musical object to format.
   * @param context the {@link SongFormatContext}.
   * @return the given {@code object} formatted as text.
   */
  String write(T object, SongFormatContext context);

  /**
   * @param object the musical object to format.
   * @return the given {@code object} formatted as text.
   */
  String write(T object);

}
