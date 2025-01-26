package io.github.musicdoc.search;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import io.github.mmm.property.WritableProperty;
import io.github.musicdoc.entity.EntityWithTitle;
import io.github.musicdoc.property.StringNormalizer;

/**
 * {@link AtomicQuery} for a generic {@link String} search.
 */
public class StringQuery extends ValueQuery<String> {

  private final SearchQueryMode mode;

  private String[] tokens;

  /**
   * The constructor.
   *
   * @param value the {@link String} to search for.
   * @param mode the {@link SearchQueryMode}.
   * @param propertyName the {@link #getPropertyName() property name}.
   */
  public StringQuery(String value, SearchQueryMode mode, String propertyName) {

    // TODO: move to repository
    super(StringNormalizer.normalize(value), propertyName);
    Objects.requireNonNull(mode);
    this.mode = mode;
  }

  /**
   * @return the {@link SearchQueryMode}.
   */
  public SearchQueryMode getMode() {

    return this.mode;
  }

  @Override
  protected Class<String> getValueClass() {

    return String.class;
  }

  private String[] getTokens() {

    if (this.tokens == null) {
      this.tokens = this.query.split(" ");
    }
    return this.tokens;
  }

  @Override
  protected double matchProperty(WritableProperty<String> property) {

    // should only happen for string based properties that are not SearchableStringProperty
    return matchNormalizedValue(StringNormalizer.normalize(property.get()));
  }

  @Override
  protected double matchNormalizedValue(String normalizedText) {

    if (normalizedText == null) {
      return 0;
    }
    if (this.mode == SearchQueryMode.EQUALS) {
      if (normalizedText.equals(this.query)) {
        return 1;
      }
      return 0;
    } else {
      double queryLength = this.query.length();
      if (queryLength == 0) {
        return 0.0000001; // pointless edge-case
      }
      if (this.mode == SearchQueryMode.CONTAINS_ALL) {
        return matchContains(normalizedText);
      } else {
        Boolean containsWord = this.mode.isContainsWord(false);
        String[] queryTokens = getTokens();
        int end = queryTokens.length - 1;
        for (int i = 0; i <= end; i++) {
          String token = queryTokens[i];
          if (i == end) {
            containsWord = this.mode.isContainsWord(true);
          }
          boolean matches = matchToken(normalizedText, token, containsWord.booleanValue());
          if (!matches) {
            return 0;
          }
        }
        double score = normalizedText.length();
        return queryLength / score;
      }
    }
  }

  private double matchContains(String normalizedText) {

    int start = normalizedText.indexOf(this.query);
    if (start >= 0) {
      int textLength = normalizedText.length();
      int queryLength = this.query.length();
      int delta = textLength - queryLength;
      if (delta == 0) {
        return 1;
      }
      // wordScore: 1 = full word matched, 0.9 = prefix or suffix, 0.8 = sub-string/infix
      double wordScore = 1;
      if ((start > 0) && (normalizedText.charAt(start - 1) != ' ')) {
        wordScore = 0.9;
      }
      int end = start + queryLength;
      if ((end < textLength) && (normalizedText.charAt(end) != ' ')) {
        if (wordScore == 1) {
          wordScore = 0.9;
        } else {
          wordScore = 0.8;
        }
      }
      double matchPercentage = queryLength;
      matchPercentage = matchPercentage / textLength;
      double score = (8 * matchPercentage) / (4 * matchPercentage * matchPercentage + 4);
      return score * wordScore;
    }
    return 0;
  }

  private boolean matchToken(String normalizedValue, String token, boolean containsWord) {

    int i = normalizedValue.indexOf(token);
    if (i < 0) {
      return false;
    }
    if (containsWord) {
      if ((i > 0) && (normalizedValue.charAt(i - 1) != ' ')) {
        return false;
      }
      int end = i + token.length();
      if ((end < normalizedValue.length()) && (normalizedValue.charAt(end) != ' ')) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected double matchAnyProperty(EntityWithTitle entity) {

    Boolean containsWordObject = this.mode.isContainsWord(false);
    boolean containsWord = false;
    boolean containsLastWord = false;
    Set<String> tokenSet = null;
    String lastToken = null;
    if (containsWordObject != null) {
      containsWord = containsWordObject.booleanValue();
      containsLastWord = this.mode.isContainsWord(true).booleanValue();
      String[] queryTokens = getTokens();
      int end = queryTokens.length - 1;
      tokenSet = new HashSet<>(end);
      for (int i = 0; i <= end; i++) {
        String token = queryTokens[i];
        if (i == end) {
          lastToken = token;
        } else {
          tokenSet.add(token);
        }
      }
    }
    double result = 0;
    int matchingProperties = 0;
    // for full accuracy we would first have to collect and order the properties by score
    for (WritableProperty<?> property : entity.getProperties()) {
      if (property.getValueClass() == String.class) {
        String normalizedValue;
        SearchScore propertyScore;
        if (property instanceof SearchableStringProperty) {
          SearchableStringProperty searchable = (SearchableStringProperty) property;
          normalizedValue = searchable.getNormalized();
          propertyScore = searchable.getScore();
        } else {
          normalizedValue = (String) property.get();
          normalizedValue = StringNormalizer.normalize(normalizedValue);
          propertyScore = SearchScore.AVG;
        }
        double valueScore;
        if (tokenSet == null) {
          valueScore = matchNormalizedValue(normalizedValue);
          if (valueScore == 1) {
            return propertyScore.getScore();
          }
        } else {
          int tokenMatchCount = 0;
          if (lastToken != null) {
            boolean match = matchToken(normalizedValue, lastToken, containsLastWord);
            if (match) {
              lastToken = null;
              tokenMatchCount++;
            }
          }
          Iterator<String> iterator = tokenSet.iterator();
          while (iterator.hasNext()) {
            String token = iterator.next();
            boolean match = matchToken(normalizedValue, token, containsWord);
            if (match) {
              iterator.remove();
              tokenMatchCount++;
            }
          }
          valueScore = tokenMatchCount;
          valueScore = valueScore / this.tokens.length;
        }
        if (valueScore > 0) {
          double score = valueScore * propertyScore.getScore();
          result += score;
          matchingProperties++;
        }
      }
    }
    if ((matchingProperties == 0) || (lastToken != null) || ((tokenSet != null) && !tokenSet.isEmpty())) {
      return 0; // not all tokens did match or no property at all
    }
    return result / matchingProperties;
  }

}
