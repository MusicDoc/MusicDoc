package io.github.musicdoc.property;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.musicdoc.search.SearchScore;
import io.github.musicdoc.search.SearchableStringProperty;

/**
 * {@link Property} for the title.<br>
 */
public class TitleProperty extends SearchableStringProperty {

  /** Default {@link #getName() name}. */
  public static final String NAME = "Title";

  /**
   * The constructor.
   */
  public TitleProperty() {

    this(NAME, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public TitleProperty(String name, PropertyMetadata<String> metadata) {

    super(name, SearchScore.MAX, null, metadata);
  }

}
