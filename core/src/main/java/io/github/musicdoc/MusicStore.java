package io.github.musicdoc;

import io.github.musicdoc.album.AlbumRepository;
import io.github.musicdoc.artist.ArtistRepository;
import io.github.musicdoc.genre.GenreRepository;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.song.SongRepository;

/**
 * Interface for the database or store for {@link Song}s and all other {@link io.github.musicdoc.entity.MusicalEntity
 * musical entities}.
 */
public interface MusicStore {

  /**
   * @return the {@link SongRepository}.
   */
  SongRepository getSongs();

  /**
   * @return the {@link ArtistRepository}.
   */
  ArtistRepository getArtists();

  /**
   * @return the {@link AlbumRepository}.
   */
  AlbumRepository getAlbums();

  /**
   * @return the {@link GenreRepository}.
   */
  GenreRepository getGenres();

}
