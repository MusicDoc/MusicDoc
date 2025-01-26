package io.github.musicdoc.impl;

import io.github.musicdoc.MusicStore;
import io.github.musicdoc.album.AlbumRepository;
import io.github.musicdoc.album.Albums;
import io.github.musicdoc.artist.ArtistRepository;
import io.github.musicdoc.artist.Artists;
import io.github.musicdoc.genre.GenreRepository;
import io.github.musicdoc.genre.Genres;
import io.github.musicdoc.song.SongRepository;
import io.github.musicdoc.song.Songs;

/**
 *
 */
public class MusicStoreImpl implements MusicStore {

  private final SongRepository songs;

  private final ArtistRepository artists;

  private final AlbumRepository albums;

  private final GenreRepository genres;

  /**
   * The constructor.
   */
  public MusicStoreImpl() {

    this(new Songs(), new Artists(), new Albums(), new Genres());
  }

  /**
   * The constructor.
   *
   * @param songs the {@link #getSongs() songs}.
   * @param artists the {@link #getArtists() artists}.
   * @param albums the {@link #getAlbums() albums}.
   * @param genres the {@link #getGenres() genres}.
   */
  public MusicStoreImpl(SongRepository songs, ArtistRepository artists, AlbumRepository albums,
      GenreRepository genres) {

    super();
    this.songs = songs;
    this.artists = artists;
    this.albums = albums;
    this.genres = genres;
  }

  @Override
  public SongRepository getSongs() {

    return this.songs;
  }

  @Override
  public ArtistRepository getArtists() {

    return this.artists;
  }

  @Override
  public AlbumRepository getAlbums() {

    return this.albums;
  }

  @Override
  public GenreRepository getGenres() {

    return this.genres;
  }

}
