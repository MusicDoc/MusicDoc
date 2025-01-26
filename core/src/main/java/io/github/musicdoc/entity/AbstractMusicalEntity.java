package io.github.musicdoc.entity;

import io.github.musicdoc.property.StringNormalizer;

/**
 * Abstract base class for a musical entity such as {@link io.github.musicdoc.genre.MusicalGenre} or
 * {@link io.github.musicdoc.artist.MusicalArtist}.
 */
public abstract class AbstractMusicalEntity implements MusicalEntity {

  private String name;

  private String normalizedName;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name} of this entity.
   */
  public AbstractMusicalEntity(String name) {

    super();
    this.name = name;
  }

  /**
   * @param entityName the {@link #getName() entity name}.
   * @return the {@link #getNormalizedName() normalized name}.
   */
  protected String normalize(String entityName) {

    return StringNormalizer.normalize(entityName);
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public String getNormalizedName() {

    if (this.normalizedName == null) {
      this.normalizedName = normalize(this.name);
    }
    return this.normalizedName;
  }

  void setName(String name) {

    this.name = name;
    this.normalizedName = null;
  }

  @Override
  public String toString() {

    return this.name;
  }

}
