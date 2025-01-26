package io.github.musicdoc.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.musicdoc.search.SearchHit;
import io.github.musicdoc.search.SearchQuery;
import io.github.musicdoc.search.SearchScore;

/**
 * {@link AbstractEntityRepository} that stores all {@link MusicalEntity entities} in a {@link Collection}.
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public abstract class CollectionEntityRepository<E extends MusicalEntity> extends AbstractEntityRepository<E> {

  /**
   * @return the {@link Collection} with all entities.
   */
  protected abstract Collection<E> getAll();

  @Override
  public List<E> find(SearchQuery query) {

    List<SearchHit<E>> hits = null;
    for (E entity : getAll()) {
      SearchScore score = query.match(entity);
      if (score.isMatch()) {
        if (hits == null) {
          hits = new ArrayList<>();
        }
        hits.add(new SearchHit<>(score, entity));
      }
    }
    if (hits == null) {
      return Collections.emptyList();
    }
    return hits.stream().sorted(Comparator.reverseOrder()).map(h -> h.getEntity()).toList();
  }

  @Override
  // default implementation may be overridden for better performance
  protected E getNormalized(String normalizedName) {

    for (E entity : getAll()) {
      if (entity.getNormalizedName().equals(normalizedName)) {
        return entity;
      }
    }
    return null;
  }
}
