package io.github.musicdoc.search;

import io.github.musicdoc.property.StringNormalizer;

/**
 * {@link Enum} with the available {@link SearchQuery query} {@link StringQuery#getMode() mode}s.<br>
 * As a general rule query matching always applies {@link StringNormalizer#normalize(String) normalization} both to the
 * matched values and to the {@link StringQuery#getQuery() query string}.<br>
 * To explain the different modes, we assume our search query string is "forgiven vision" and give examples what will be
 * matched by the different modes:
 * <ul>
 * <li>{@link #EQUALS} will only match if the value is entirely equal to our search query string so in our example
 * "forgiven vision".</li>
 * <li>{@link #CONTAINS_ALL} will only match if the value contains our search query string so in our example e.g. "my
 * forgiven visionary sight" or "unforgiven vision".</li>
 * <li>{@value #CONTAINS_TOKENS} will match if the value contains each token (word) of our search query string so in our
 * example e.g. "visionary unforgiven sin".</li>
 * <li>{@link #CONTAINS_WORDS} will match if the value contains each token of our search query string as whole word so
 * in our example e.g. "my vision of forgiven sins" or "vision forgiven" but not "unforgiven vision" or "forgiven
 * visionary".</li>
 * <li>{@link #CONTAINS_WORDS_EXCEPT_LAST_TOKEN} will match if the value contains each token of our search query string
 * as whole word but allows the last token to be contained so in our example e.g. "visionary view of forgiven
 * dreams".</li>
 * </ul>
 *
 * @see StringQuery#getMode()
 */
public enum SearchQueryMode {

  /**
   * Tokenizes the {@link StringQuery#getQuery() query} and matches if all tokens are contained as whole "words" except
   * for the last token that may be contained.
   */
  CONTAINS_WORDS_EXCEPT_LAST_TOKEN,

  /** Tokenizes the {@link StringQuery#getQuery() query} and matches if all tokens are contained. */
  CONTAINS_TOKENS,

  /**
   * Tokenizes the {@link StringQuery#getQuery() query} and matches if all tokens are contained as whole "words".
   */
  CONTAINS_WORDS,

  /** Matches only if the entire {@link StringQuery#getQuery() query} is contained. */
  CONTAINS_ALL,

  /** Matches only if the entire {@link StringQuery#getQuery() query} equals. */
  EQUALS;

  /**
   * @param lastToken {@code true} for the last token of a {@link SearchQuery}, {@code false} otherwise.
   * @return {@link Boolean#TRUE} if the token shall be {@link #CONTAINS_WORDS contained as whole word},
   *         {@link Boolean#FALSE} if the token shall be {@link #CONTAINS_TOKENS contained as token}, and {@code null}
   *         otherwise.
   */
  public Boolean isContainsWord(boolean lastToken) {

    switch (this) {
      case CONTAINS_WORDS:
        return Boolean.TRUE;
      case CONTAINS_WORDS_EXCEPT_LAST_TOKEN:
        return Boolean.valueOf(!lastToken);
      case CONTAINS_TOKENS:
        return Boolean.FALSE;
      default:
        return null;
    }
  }

}
