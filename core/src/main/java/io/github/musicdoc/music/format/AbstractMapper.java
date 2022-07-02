package io.github.musicdoc.music.format;

import java.util.Locale;

import io.github.musicdoc.io.MusicInputStream;

/**
 * Abstract base class for a mapper that is both a {@link MusicFormatter} and a {@link MusicParser}.
 *
 * @param <T> type of the musical object to map.
 */
public abstract class AbstractMapper<T> implements MusicParser<T>, MusicFormatter<T>, FormatConstants {

  /**
   * @return the {@link SongFormat}.
   */
  protected abstract SongFormat getFormat();

  /**
   * Gives access to {@link #getFormat()}.
   *
   * @param mapper the {@link AbstractMapper}.
   * @return the {@link #getFormat() format} of the given {@link AbstractMapper}.
   */
  protected SongFormat getFormat(AbstractMapper<?> mapper) {

    if (mapper == null) {
      return null;
    }
    return mapper.getFormat();
  }

  @Override
  public String format(T object) {

    return format(object, new SongFormatOptions(getFormat()));
  }

  @Override
  public T parse(MusicInputStream chars) {

    return parse(chars, new SongFormatOptions(getFormat()));
  }

  /**
   * @param string the {@link String} to convert.
   * @return the given {@link String} converted to lower case without {@link Locale} side-effects.
   */
  protected static String toLowerCase(String string) {

    if (string == null) {
      return null;
    }
    return string.toLowerCase(Locale.ROOT);
  }

}
