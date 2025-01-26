package io.github.musicdoc.entity;

import java.util.List;

import io.github.musicdoc.genre.MusicalGenre;
import io.github.musicdoc.search.SearchQuery;

/**
 * Interface for a repository that gives access to {@link #get(String) get}, {@link #add(MusicalEntity) add}, and
 * {@link #remove(MusicalEntity) remove} an {@link MusicalEntity entity}. Unlike generic database access this interface
 * is designed for a limited amount of data that can also be held in memory. It can be seen as the access to a specific
 * table of a database. It may also be implemented as a simple in-memory store that loads from and saves to a flat file
 * (e.g. in YAML format).
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public interface EntityRepository<E extends MusicalEntity> {

  /**
   * @param name the {@link MusicalEntity#getName() name} of the requested {@link MusicalEntity entity}.
   * @return the entity with the given {@link MusicalEntity#getName() name} or {@code null} if not found.
   */
  E get(String name);

  /**
   * @param name the {@link MusicalEntity#getName() name} of the requested {@link MusicalEntity entity}.
   * @return the {@link MusicalEntity entity} with the given {@link MusicalGenre#getName() name}. Will be created and
   *         {@link #add(MusicalEntity) added} if it does not already exist.
   * @see #get(String)
   * @see #add(MusicalEntity)
   */
  E getOrCreate(String name);

  /**
   * @param name the {@link MusicalEntity#getName() entity name}.
   * @return the newly created {@link MusicalEntity entity}.
   */
  E create(String name);

  /**
   * @param entity the {@link MusicalEntity} to rename.
   * @param newName the new {@link MusicalEntity#getName() name}.
   */
  void rename(E entity, String newName);

  /**
   * @param query the {@link SearchQuery search query}.
   * @return the {@link List} with all {@link MusicalEntity entities} matching the given {@link SearchQuery search
   *         query}.
   */
  List<E> find(SearchQuery query);

  /**
   * @param entity the {@link MusicalEntity} to add to this repository.
   */
  void add(E entity);

  /**
   * @param entity the {@link MusicalEntity} to remove from this repository. <b>ATTENTION:</b> this is a sensitive
   *        operation and may not always be allowed.
   * @return {@code true} if the given {@link MusicalEntity entity} has been successfully removed from this repository,
   *         {@code false} otherwise (was not {@link #add(MusicalEntity) added} before).
   */
  boolean remove(E entity);

}
