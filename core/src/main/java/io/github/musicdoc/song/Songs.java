package io.github.musicdoc.song;

import io.github.mmm.orm.memory.SequenceMemoryRepository;

/**
 * Default implementation of {@link SongRepository}.
 */
public class Songs extends SequenceMemoryRepository<Song> implements SongRepository {

  /**
   * The constructor.
   */
  public Songs() {

    super(Song.of());
  }

}
