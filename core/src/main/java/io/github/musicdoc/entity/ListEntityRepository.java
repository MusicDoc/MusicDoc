package io.github.musicdoc.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * {@link AbstractEntityRepository} that stores all {@link MusicalEntity entities} in a {@link Map}.
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public abstract class ListEntityRepository<E extends MusicalEntity> extends CollectionEntityRepository<E> {

  /** {@link List} of {@link MusicalEntity entities}. */
  protected final List<E> entities;

  /**
   * The constructor.
   */
  public ListEntityRepository() {

    this(new ArrayList<>()); // ConcurrentLinkedQueue?
  }

  /**
   * The constructor.
   *
   * @param entities the underlying {@link List} to store the {@link MusicalEntity entities}.
   */
  protected ListEntityRepository(List<E> entities) {

    super();
    this.entities = entities;
  }

  @Override
  protected Collection<E> getAll() {

    return this.entities;
  }

  @Override
  public void add(E entity) {

    assert (!this.entities.contains(entity));
    this.entities.add(entity);
  }

  @Override
  public boolean remove(E entity) {

    if (entity == null) {
      return false;
    }
    return this.entities.remove(entity);
  }

}
