package io.github.musicdoc.artist;

import java.util.Collection;
import java.util.List;

import io.github.mmm.orm.memory.SequenceMemoryRepository;
import io.github.mmm.orm.memory.index.MemoryIndex;
import io.github.mmm.orm.memory.index.MemoryUniqueIndex;

/**
 * This class represents a database of all available {@link Artist}s. It acts as the collection and manager to get
 * access to a {@link Artist}.
 */
public class Artists extends SequenceMemoryRepository<Artist> implements ArtistRepository {

  private final MemoryUniqueIndex<String, Artist> titleIndex;

  private final List<MemoryIndex<?, ? super Artist>> indices;

  /**
   * The constructor.
   */
  public Artists() {

    super(Artist.of());
    this.titleIndex = new MemoryUniqueIndex<>(this, this.prototype.Title().getName());
    this.indices = List.of(this.titleIndex);
  }

  @Override
  protected Collection<MemoryIndex<?, ? super Artist>> getIndices() {

    return this.indices;
  }

  @Override
  public Artist findByTitle(String title) {

    return this.titleIndex.find(title);
  }

}
