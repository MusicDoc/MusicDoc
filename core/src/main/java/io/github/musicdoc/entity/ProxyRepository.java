package io.github.musicdoc.entity;

import io.github.mmm.entity.id.Id;
import io.github.mmm.orm.repository.EntityRepository;

/**
 * Implementation of {@link EntityRepository} that acts as proxy and delegates all operations to another instance.
 *
 * @param <E> type of the managed {@link MusicalEntity}.
 */
public class ProxyRepository<E extends EntityWithTitle> implements EntityRepository<E> {

  private final EntityRepository<E> repository;

  /**
   * The constructor.
   *
   * @param repository the {@link EntityRepository} to wrap as proxy.
   */
  public ProxyRepository(EntityRepository<E> repository) {

    super();
    this.repository = repository;
  }

  @Override
  public Class<E> getEntityClass() {

    return this.repository.getEntityClass();
  }

  @Override
  public E findById(Id<E> id) {

    return this.repository.findById(id);
  }

  @Override
  public Id<E> save(E entity) {

    return this.repository.save(entity);
  }

  @Override
  public boolean delete(E entity) {

    return this.repository.delete(entity);
  }

  @Override
  public boolean deleteById(Id<E> id) {

    return this.repository.deleteById(id);
  }

  // @Override
  // public List<E> find(SearchQuery query) {
  //
  // return this.repository.find(query);
  // }

}
