package io.github.musicdoc.search;

import io.github.mmm.bean.Bean;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.entity.EntityWithTitle;

/**
 * Class representing a query to search for a specific {@link EntityWithTitle}.
 */
public abstract class SearchQuery {

  /**
   * @param entity the {@link MusicalEntity} to match.
   * @return the resulting {@link SearchScore}.
   */
  public final SearchScore match(EntityWithTitle entity) {

    double score = doMatch(entity);
    return SearchScore.of(score);
  }

  /**
   * @param entity the {@link MusicalEntity} to match. Not a {@link Bean} (or {@link io.github.musicdoc.song.Song}).
   * @return the resulting {@link SearchScore#getScore() search score}.
   */
  protected abstract double doMatch(EntityWithTitle entity);

}
