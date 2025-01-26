package io.github.musicdoc.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.github.mmm.base.exception.DuplicateObjectException;
import io.github.mmm.base.exception.ReadOnlyException;

/**
 * {@link AbstractEntityRepository} that stores all {@link MusicalEntity entities} in a {@link Map}.
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public abstract class MapEntityRepository<E extends MusicalEntity> extends CollectionEntityRepository<E> {

  /** {@link Map} to map from {@link MusicalEntity#getNormalizedName() normalized name} to {@link MusicalEntity}. */
  protected final Map<String, E> entityMap;

  /**
   * The constructor.
   */
  public MapEntityRepository() {

    this(new HashMap<>()); // ConcurrentHashMap?
  }

  /**
   * The constructor.
   *
   * @param entityMap the underlying {@link Map} to store the {@link MusicalEntity entities}.
   */
  protected MapEntityRepository(Map<String, E> entityMap) {

    super();
    this.entityMap = entityMap;
  }

  @Override
  protected Collection<E> getAll() {

    return this.entityMap.values();
  }

  @Override
  protected E getNormalized(String normalizedName) {

    return this.entityMap.get(normalizedName);
  }

  @Override
  public void add(E entity) {

    if (isReadOnly()) {
      throw new ReadOnlyException(this);
    }
    String key = entity.getNormalizedName();
    E duplicate = this.entityMap.put(key, entity);
    if ((duplicate != null) && (duplicate != entity)) {
      throw new DuplicateObjectException(entity, key, duplicate);
    }
  }

  @Override
  public boolean remove(E entity) {

    if (isReadOnly()) {
      throw new ReadOnlyException(this);
    }
    if (entity == null) {
      return false;
    }
    String key = entity.getNormalizedName();
    return this.entityMap.remove(key) != null;
  }

}
