package io.github.musicdoc.genre;

import java.util.Collection;
import java.util.List;

import io.github.mmm.entity.id.Id;
import io.github.mmm.orm.memory.SequenceMemoryRepository;
import io.github.mmm.orm.memory.index.MemoryIndex;
import io.github.mmm.orm.memory.index.MemoryUniqueIndex;
import io.github.musicdoc.property.StringNormalizer;

/**
 * Implementation of {@link GenreRepository}.
 */
public class Genres extends SequenceMemoryRepository<Genre> implements GenreRepository {

  private final MemoryUniqueIndex<String, Genre> titleOrSynonymIndex;

  private final List<MemoryIndex<?, ? super Genre>> indices;

  /**
   * The constructor.
   */
  public Genres() {

    super(Genre.of());
    Genre genre = getPrototype();
    this.titleOrSynonymIndex = new MemoryUniqueIndex<>(this, StringNormalizer.get(), genre.Title().getName(),
        genre.Synonyms().getName());
    this.indices = List.of(this.titleOrSynonymIndex);
  }

  @Override
  protected Collection<MemoryIndex<?, ? super Genre>> getIndices() {

    return this.indices;
  }

  @Override
  public boolean doDeleteById(Id<Genre> id) {

    Object object = id.get();
    if (object instanceof Long l) {
      long pk = l.longValue();
      if (pk <= Genre.PK_MUSIC) {
        throw new IllegalStateException("Genre with ID " + id
            + " is build-in and cannot be deleted. Only Genres with IDs greater than 210 are allowed to be deleted!");
      }
    }
    return super.doDeleteById(id);
  }

  @Override
  public Genre findByTitleOrSynonym(String titleOrSynonym) {

    return this.titleOrSynonymIndex.find(titleOrSynonym);
  }

}
