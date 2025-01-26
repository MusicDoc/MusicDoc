package io.github.musicdoc.album;

import io.github.mmm.bean.BeanFactory;
import io.github.mmm.entity.property.link.LinkProperty;
import io.github.mmm.property.time.year.YearProperty;
import io.github.musicdoc.artist.Artist;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.genre.Genre;

/**
 * Album of a {@link io.github.musicdoc.song.Song}. An album is a collection of songs such as a CD, LP, or single. If a
 * user creates a new {@link io.github.musicdoc.song.Song} and assign an existing {@link Album album} both the
 * {@link #Genre()} and the {@link #Artist()} will be preselected. This increases the usability for the user and can
 * save some time while the user still has the flexibility to change these values as desired.<br>
 *
 * @see io.github.musicdoc.song.Song#Album
 */
public interface Album extends MusicalEntity {

  /**
   * @return the {@link Genre} of this album. Typically all {@link io.github.musicdoc.song.Song songs} from the same
   *         album also share the same genre.
   */
  LinkProperty<Genre> Genre();

  /**
   * @return the {@link Artist} of this album or {@code null} if undefined or the album is a sampler that has no artist
   *         since each {@link io.github.musicdoc.song.Song} may be from a different artist.
   */
  LinkProperty<Artist> Artist();

  /**
   * @return the {@link java.time.Year} when the album was released. Will be {@code null} or {@code 0} if undefined.
   */
  YearProperty Year();

  /**
   * @return a new instance of {@link Album}.
   */
  static Album of() {

    return BeanFactory.get().create(Album.class);
  }

}
