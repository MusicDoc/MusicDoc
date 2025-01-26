package io.github.musicdoc.song;

import java.util.Locale;

import io.github.musicdoc.search.SearchQuery;

/**
 * Interface for a filter that decides which songs to {@link #accept(Song) accept} so all others are filtered out.
 *
 * @deprecated replaced by {@link SearchQuery}.
 */
@Deprecated
public interface SongFilter {

  /**
   * @param song the {@link Song} to filter.
   * @return {@code true} to accept the given {@link Song}, {@code false} otherwise (filter out).
   */
  boolean accept(Song song);

  static SongFilter of(String search) {

    if ((search == null) || search.isEmpty()) {
      return s -> true;
    }
    return new SongFilterDefault(search);
  }

  static String normalize(String string) {

    return string.toLowerCase(Locale.ROOT);
  }
}
