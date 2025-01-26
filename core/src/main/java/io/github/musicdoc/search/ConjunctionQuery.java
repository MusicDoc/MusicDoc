package io.github.musicdoc.search;

import io.github.musicdoc.entity.EntityWithTitle;

/**
 * {@link SearchQuery} that joins any given number of sub-{@link SearchQuery queries} with the conjunction
 * {@link #and(SearchQuery...) AND} or {@link #or(SearchQuery...) OR}.
 *
 * @see AtomicQuery
 */
public final class ConjunctionQuery extends SearchQuery {

  private final boolean and;

  private final SearchQuery[] terms;

  private ConjunctionQuery(boolean and, SearchQuery... terms) {

    super();
    if ((terms == null) || (terms.length == 0)) {
      throw new IllegalArgumentException("Empty conjunction!");
    }
    this.and = and;
    this.terms = terms;
  }

  @Override
  protected double doMatch(EntityWithTitle entity) {

    double result = 0;
    for (SearchQuery query : this.terms) {
      double score = query.doMatch(entity);
      if (this.and) {
        if (score == 0) {
          return 0;
        }
        result += score;
      } else {
        if (score == 1) {
          return 1;
        } else if (result < score) {
          result = score;
        }
      }
    }
    if (this.and) {
      result = result / this.terms.length;
    }
    return result;
  }

  /**
   * @param terms the {@link SearchQuery queries} to combine with the fuzzy {@code AND} operator.
   * @return a {@link ConjunctionQuery} that returns the average of the {@link SearchScore}s but results in
   *         {@link SearchScore#MIN} if one sub-query evaluated to that score.
   */
  public static ConjunctionQuery and(SearchQuery... terms) {

    return new ConjunctionQuery(true, terms);
  }

  /**
   * @param terms the {@link SearchQuery queries} to combine with the fuzzy {@code OR} operator.
   * @return a {@link ConjunctionQuery} that returns the maximum of the {@link SearchScore}s.
   */
  public static ConjunctionQuery or(SearchQuery... terms) {

    return new ConjunctionQuery(false, terms);
  }

}
