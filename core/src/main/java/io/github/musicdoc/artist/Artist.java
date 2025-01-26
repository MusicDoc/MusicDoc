package io.github.musicdoc.artist;

import io.github.mmm.bean.BeanFactory;
import io.github.mmm.entity.property.link.LinkProperty;
import io.github.mmm.property.time.year.YearProperty;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.genre.Genre;

/**
 * Artist of a {@link io.github.musicdoc.song.Song}. This is typically the original person or band performing the piece
 * of music. It is explicitly distinguished from the composer or lyricist of the song even though for some songs the
 * artist and the composer may be the same person. Unlike the composer or lyricist the artist may not be a natural
 * person but rather a group of people like a band or an orchestra.
 *
 * @see io.github.musicdoc.song.Song#Artist
 */
public interface Artist extends MusicalEntity {

  /**
   * @return the default {@link Genre} of this artist. Various artists mainly performed
   *         {@link io.github.musicdoc.song.Song songs} of a specific genre. If this is associated here, users that
   *         create a new {@link io.github.musicdoc.song.Song} and assign an {@link Artist artist} from the database of
   *         common artists this default genre can be preselected for the genre of the new
   *         {@link io.github.musicdoc.song.Song}. This increases the usability for the user and can save some time
   *         while the user still has the flexibility to change the genre as desired.<br>
   *         Some virtuous artists performed songs of such different genres that they can not be pinned to a default
   *         genre. Also the user can choose an artist that is not available in the database of known artists. In such
   *         cases there is no default genre and the value will be undefined ({@code null}).
   */
  LinkProperty<Genre> Genre();

  /**
   * @return the {@link java.time.Year} when the artist or band started performing and publishing music.
   */
  YearProperty Year();

  /**
   * @return a new instance of {@link Artist}.
   */
  static Artist of() {

    return BeanFactory.get().create(Artist.class);
  }

}
