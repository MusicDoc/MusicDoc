package io.github.musicdoc.search;

import java.util.Set;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.string.StringSetProperty;
import io.github.musicdoc.entity.EntityWithTitle;

/**
 * {@link ValueQuery} for a search on tags.
 *
 * @see io.github.musicdoc.song.Song#Tags
 */
public class TagsQuery extends AtomicQuery<String> {

  private final boolean exclude;

  private final String[] tags;

  /**
   * The constructor.
   *
   * @param exclude - {@code true} for an exclude where the given tags shall NOT be present, {@code false} otherwise
   *        (for an include where all tags shall be present).
   * @param tags the individual tags to search.
   */
  public TagsQuery(boolean exclude, String... tags) {

    super("Tags");
    this.exclude = exclude;
    this.tags = tags;
  }

  @Override
  protected Class<String> getValueClass() {

    return String.class;
  }

  @Override
  protected double matchNormalizedValue(String normalizedValue) {

    return 0;
  }

  @Override
  protected double matchProperty(WritableProperty<String> property) {

    if (property instanceof StringSetProperty) {
      return matchProperty(property);
    }
    return 0;
  }

  private double matchProperty(StringSetProperty property) {

    Set<String> tagSet = property.getAsSet();
    for (String tag : this.tags) {
      boolean contains = tagSet.contains(tag);
      if (this.exclude == contains) {
        return 0;
      }
    }
    return 1;
  }

  @Override
  protected double matchAnyProperty(EntityWithTitle entity) {

    double result = 0;
    for (WritableProperty<?> property : entity.getProperties()) {
      if (property instanceof StringSetProperty) {
        double score = matchProperty((StringSetProperty) property);
        if (score > result) {
          result = score;
        }
      }
    }
    return result;
  }

}
