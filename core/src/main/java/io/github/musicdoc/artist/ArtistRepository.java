package io.github.musicdoc.artist;

import io.github.mmm.orm.repository.EntityRepository;

/**
 * {@link EntityRepository Repository} for {@link Artist}s.
 */
public interface ArtistRepository extends EntityRepository<Artist> {

  /**
   * @param title the {@link Artist#Title() title} to search for.
   * @return the matching {@link Artist} or {@code null} if not found.
   */
  Artist findByTitle(String title);

}
