package io.github.musicdoc.album;

import io.github.musicdoc.search.SearchScore;
import io.github.musicdoc.search.SearchableStringProperty;

/**
 * {@link SearchableStringProperty} with {@link MusicalAlbum} as {@link #get() value}.
 */
public class AlbumProperty extends SearchableStringProperty {

  /** Default {@link #getName() name}. */
  public static final String NAME = "Album";

  /**
   * The constructor.
   */
  public AlbumProperty() {

    super(NAME, SearchScore.AVG, null, null);
  }

  /**
   * @param repository the {@link AlbumRepository}.
   * @return the {@link #get() value} as {@link MusicalAlbum}.
   */
  public MusicalAlbum getAsAlbum(AlbumRepository repository) {

    String albumName = get();
    if ((albumName == null) || albumName.isEmpty()) {
      return null;
    }
    MusicalAlbum album = repository.getOrCreate(albumName);
    return album;
  }

  /**
   * @param album the {@link MusicalAlbum} to set.
   */
  public void setAsAlbum(MusicalAlbum album) {

    if (album == null) {
      set(null);
    } else {
      set(album.getName());
    }
  }
}
