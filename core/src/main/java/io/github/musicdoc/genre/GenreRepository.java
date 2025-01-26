package io.github.musicdoc.genre;

import io.github.mmm.entity.id.Id;
import io.github.mmm.entity.id.PkIdLong;
import io.github.mmm.orm.repository.EntityRepository;

/**
 * {@link EntityRepository Repository} for {@link Genre}s.
 */
public interface GenreRepository extends EntityRepository<Genre> {

  /**
   * @param id3Code the ID3 code.
   * @return the {@link Genre} with the given ID3 code or {@code null} if no such genre exists.
   */
  default Genre findById3(int id3Code) {

    if ((id3Code < 0) || (id3Code > 255)) {
      return null;
    }
    return findByPk(id3Code);
  }

  /**
   * @param pk the {@link Id#getPk() primary key}.
   * @return the requested {@link Genre} or {@code null} if not found.
   */
  default Genre findByPk(long pk) {

    return findById(new PkIdLong<>(Genre.class, Long.valueOf(pk)));
  }

  /**
   * @param titleOrSynonym the {@link Genre#Title() title} or {@link Genre#Synonyms() synonym} to search for.
   * @return the matching {@link Genre} or {@code null} if not found.
   */
  Genre findByTitleOrSynonym(String titleOrSynonym);

}
