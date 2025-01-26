package io.github.musicdoc.song;

import java.util.Comparator;

import io.github.mmm.orm.repository.EntityRepository;

/**
 * Interface for {@link EntityRepository repository} of {@link Song}s.
 */
public interface SongRepository extends EntityRepository<Song> {

  /** {@link Comparator} to sort {@link Song}s by {@link Song#Title title}. */
  Comparator<Song> SORT_BY_TITLE = (s1, s2) -> s1.Title().get().compareTo(s2.Title().get());

  /** {@link Comparator} to sort {@link Song}s by {@link Song#Artist artist}. */
  Comparator<Song> SORT_BY_ARTIST = (s1, s2) -> {
    int result = s1.Artist().getEntity().Title().get().compareTo(s2.Artist().getEntity().Title().get());
    if (result == 0) {
      result = s1.Title().get().compareTo(s2.Title().get());
    }
    return result;
  };

}
