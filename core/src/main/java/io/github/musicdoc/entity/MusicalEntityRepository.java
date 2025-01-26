package io.github.musicdoc.entity;

import io.github.mmm.orm.repository.EntityRepository;
import io.github.musicdoc.album.Album;

/**
 * {@link EntityRepository Repository} for {@link MusicalEntity musical entities}.
 *
 * @param <E> type of the {@link MusicalEntity}.
 */
public interface MusicalEntityRepository<E extends MusicalEntity> extends EntityRepository<E> {

  /**
   * @param title the {@link MusicalEntity#Title() title}.
   * @return the {@link Iterable} of {@link MusicalEntity entities} with the given {@link MusicalEntity#Title() title}
   *         value. Typically the {@link Iterable} will have one or no element. However, it is also possible to have
   *         multiple matching results. E.g. the {@link Album#Title() album title} "Faith" was used both by "The Cure"
   *         and by "George Michael". The album "Feels Like Home" was even used by "Linda Ronstadt", "Norah Jones", and
   *         "Sheryl Crow".
   *
   */
  Iterable<E> findByTitle(String title);

}
