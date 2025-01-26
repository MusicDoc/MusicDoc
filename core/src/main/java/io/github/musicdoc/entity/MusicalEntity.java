package io.github.musicdoc.entity;

import io.github.mmm.bean.AbstractInterface;
import io.github.mmm.property.string.StringProperty;

/**
 * Interface for a musical entity such as {@link io.github.musicdoc.song.Song}, {@link io.github.musicdoc.genre.Genre},
 * or {@link io.github.musicdoc.artist.Artist}.
 */
@AbstractInterface
public interface MusicalEntity extends EntityWithTitle {

  /**
   * @return the title (display name) of the entity (e.g. "Chiquitita" for a song, "Voulez-Vous" for an album, "ABBA"
   *         for an artist, or "Soft rock" for a genre).
   */
  @Override
  StringProperty Title();

}
