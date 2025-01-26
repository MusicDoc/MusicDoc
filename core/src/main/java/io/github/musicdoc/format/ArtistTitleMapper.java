package io.github.musicdoc.format;

import io.github.musicdoc.artist.Artist;

/**
 * {@link TitleMapper} for {@link Artist}.
 */
public class ArtistTitleMapper extends TitleMapper<Artist> {

  /** The singleton instance. */
  public static final ArtistTitleMapper INSTANCE = new ArtistTitleMapper();

  @Override
  protected Artist getByTitle(String title) {

    Artist artist = Artist.of();
    artist.Title().set(title);
    return artist;
  }

}
