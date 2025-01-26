package io.github.musicdoc.format;

import io.github.musicdoc.album.Album;

/**
 * {@link TitleMapper} for {@link Album}.
 */
public class AlbumTitleMapper extends TitleMapper<Album> {

  /** The singleton instance. */
  public static final AlbumTitleMapper INSTANCE = new AlbumTitleMapper();

  @Override
  protected Album getByTitle(String title) {

    Album album = Album.of();
    album.Title().set(title);
    return album;
  }

}
