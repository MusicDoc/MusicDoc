package io.github.musicdoc.search;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.string.StringProperty;
import io.github.musicdoc.property.StringNormalizer;

/**
 * Extends {@link StringProperty} with the ability to get the {@link #getNormalized() normalized value}.
 */
public class SearchableStringProperty extends StringProperty {

  private final SearchScore score;

  private String normalized;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public SearchableStringProperty(String name) {

    this(name, null, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param score the {@link #getScore() search score}.
   */
  public SearchableStringProperty(String name, SearchScore score) {

    this(name, score, null, null);

  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param score the {@link #getScore() search score}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public SearchableStringProperty(String name, SearchScore score, String value, PropertyMetadata<String> metadata) {

    super(name, value, metadata);
    if (score == null) {
      if ("Title".equals(name)) {
        this.score = SearchScore.MAX;
      } else if ("Artist".equals(name) || "Composer".equals(name)) {
        this.score = SearchScore.VERY_HIGH;
      } else {
        this.score = SearchScore.HIGH;
      }
    } else {
      this.score = score;
    }
  }

  @Override
  protected void doSet(String newValue) {

    super.doSet(newValue);
    this.normalized = null;
  }

  /**
   * @return the normalized {@link #get() value}.
   */
  public String getNormalized() {

    if (this.normalized == null) {
      this.normalized = StringNormalizer.normalize(get());
    }
    return this.normalized;
  }

  /**
   * @return the {@link SearchScore} of this property.
   */
  public SearchScore getScore() {

    return this.score;
  }

}
