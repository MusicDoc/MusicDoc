package io.github.musicdoc.entity;

import java.util.Objects;

import io.github.mmm.base.exception.DuplicateObjectException;
import io.github.mmm.base.exception.ReadOnlyException;
import io.github.musicdoc.property.StringNormalizer;

/**
 * Abstract base implementation of {@link EntityRepository}.
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public abstract class AbstractEntityRepository<E extends MusicalEntity> implements EntityRepository<E> {

  @Override
  public final E get(String name) {

    String key = normalize(name);
    return getNormalized(key);
  }

  @Override
  public E getOrCreate(String name) {

    Objects.requireNonNull(name);
    E entity = get(name);
    if (entity == null) {
      requireMutable();
      entity = create(name);
      add(entity);
    }
    return entity;
  }

  @Override
  public void rename(E entity, String newName) {

    Objects.requireNonNull(entity);
    Objects.requireNonNull(newName);
    if (newName.isBlank()) {
      throw new IllegalArgumentException(newName);
    }
    E duplicate = get(newName);
    if (duplicate != null) {
      throw new DuplicateObjectException(entity, newName);
    }
    boolean managed = remove(entity);
    doRename(entity, newName, managed);
    if (managed) {
      add(entity);
    }
  }

  /**
   * @param entity the {@link MusicalEntity} to rename.
   * @param newName the new {@link MusicalEntity#getName() name}.
   * @param managed - {@code true} if the given {@link MusicalEntity entity} to rename is managed by this repository,
   *        {@code false} otherwise (transient entity).
   */
  protected void doRename(E entity, String newName, boolean managed) {

    ((AbstractMusicalEntity) entity).setName(newName);
  }

  /**
   * @param name the potential {@link MusicalEntity#getName() entity name}.
   * @return the {@link MusicalEntity#getNormalizedName() normalized name}.
   */
  protected String normalize(String name) {

    return StringNormalizer.normalize(name);
  }

  /**
   * @param normalizedName the {@link MusicalEntity#getNormalizedName() normalized name}
   * @return the {@link #get(String) requested entity}.
   */
  protected abstract E getNormalized(String normalizedName);

  /**
   * @return {@code true} if this repository is read-only, {@code false} otherwise (mutable).
   */
  protected boolean isReadOnly() {

    return false;
  }

  /**
   * Verifies that this repository is NOT {@link #isReadOnly() read-only}.
   */
  protected void requireMutable() {

    if (isReadOnly()) {
      throw new ReadOnlyException(this);
    }
  }

}
