package io.github.musicdoc.search;

import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.entity.EntityWithTitle;

/**
 * Container for the hit of a search as the {@link #getEntity() matching entity} together with its {@link SearchScore}.
 *
 * @param <E> type of the {@link MusicalEntity}.
 */
@SuppressWarnings("rawtypes")
public class SearchHit<E extends EntityWithTitle> implements Comparable<SearchHit> {

  private final double score;

  private final E entity;

  /**
   * The constructor.
   *
   * @param score the {@link SearchScore} of the hit.
   * @param entity the matching {@link #getEntity() entity}.
   */
  public SearchHit(SearchScore score, E entity) {

    super();
    this.score = score.getScore();
    this.entity = entity;
  }

  @Override
  public int compareTo(SearchHit o) {

    if (this.score < o.score) {
      return -1;
    } else if (this.score > o.score) {
      return 1;
    }
    // if same score, compare lexicographically (inverse because we want to have highest score first)
    String title = this.entity.Title().get();
    String otherTitle = o.entity.Title().get();
    if (title == null) {
      if (otherTitle == null) {
        return 0;
      }
      return 1;
    } else if (otherTitle == null) {
      return -1;
    }
    return -title.compareTo(otherTitle);
  }

  /**
   * @return the {@link MusicalEntity}.
   */
  public E getEntity() {

    return this.entity;
  }

  /**
   * @return score
   */
  public double getScore() {

    return this.score;
  }

  @Override
  public String toString() {

    return this.entity.toString();
  }

}
