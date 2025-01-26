package io.github.musicdoc.search;

/**
 * Simple value type for a {@link #getScore() search score}.
 */
public class SearchScore {

  /** The minimum {@link #getScore() score} that represents no match at all. */
  public static final SearchScore MIN = new SearchScore(0);

  /** An average {@link #getScore() score} for a rather relevant match. */
  public static final SearchScore AVG = new SearchScore(0.5);

  /** A high {@link #getScore() score} for a relevant match. */
  public static final SearchScore HIGH = new SearchScore(0.8);

  /** A very high {@link #getScore() score} for a very relevant match. */
  public static final SearchScore VERY_HIGH = new SearchScore(0.9);

  /** The maximum {@link #getScore() score} that represents a perfect match. */
  public static final SearchScore MAX = new SearchScore(1);

  private final double score;

  /**
   * The constructor.
   *
   * @param score
   */
  private SearchScore(double score) {

    super();
    if ((score < 0) || (score > 1)) {
      throw new IllegalArgumentException("Invalid score: " + score);
    }
    this.score = score;
  }

  /**
   * @return the search score is a double value in the range from {@code 0} to {@code 1} representing the ranking of a
   *         fuzzy search. The value {@code 0} means that there is no match at all. Any positive value indicates a match
   *         and the higher the value the more relevant the match. A value of {@code 1} is a perfect match and that
   *         score is only reached if the query equals to the matched value in the most relevant property. All matching
   *         entities to a {@link SearchQuery} are sorted descending by their score so the most relevant hits come
   *         first.
   */
  public double getScore() {

    return this.score;
  }

  /**
   * @return {@code true} if this is a match (has a positive {@link #getScore() score}), {@code false} otherwise.
   */
  public boolean isMatch() {

    return this.score > 0;
  }

  /**
   * @param score the {@link #getScore() search score} value.
   * @return a {@link SearchScore} instance for the given {@code score} value.
   */
  public static SearchScore of(double score) {

    if (score == 0) {
      return MIN;
    } else if (score == 1) {
      return MAX;
    } else if (score == VERY_HIGH.score) {
      return VERY_HIGH;
    } else if (score == HIGH.score) {
      return HIGH;
    } else if (score == AVG.score) {
      return AVG;
    }
    return new SearchScore(score);
  }

  @Override
  public String toString() {

    return String.valueOf(this.score);
  }

}
