package io.github.musicdoc.album;

import java.util.Collection;
import java.util.List;

import io.github.mmm.orm.memory.SequenceMemoryRepository;
import io.github.mmm.orm.memory.index.MemoryIndex;
import io.github.mmm.orm.memory.index.MemoryNonUniqueIndex;
import io.github.musicdoc.property.StringNormalizer;

/**
 * This class represents a database of all available {@link Album}s. It acts as the collection and manager to get access
 * to a {@link Album}.
 */
public class Albums extends SequenceMemoryRepository<Album> implements AlbumRepository {

  private final MemoryNonUniqueIndex<String, Album> titleIndex;

  private final List<MemoryIndex<?, ? super Album>> indices;

  /**
   * The constructor.
   */
  public Albums() {

    super(Album.of());
    Album album = getPrototype();
    this.titleIndex = new MemoryNonUniqueIndex<>(this, StringNormalizer.get(), album.Title().getName());
    this.indices = List.of(this.titleIndex);
  }

  @Override
  protected Collection<MemoryIndex<?, ? super Album>> getIndices() {

    return this.indices;
  }

  @Override
  public Iterable<Album> findByTitle(String title) {

    return this.titleIndex.find(title);
  }

}
