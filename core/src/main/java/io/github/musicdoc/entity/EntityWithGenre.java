package io.github.musicdoc.entity;

import io.github.mmm.bean.AbstractInterface;
import io.github.mmm.entity.bean.EntityBean;
import io.github.mmm.entity.property.link.LinkProperty;
import io.github.musicdoc.genre.Genre;

/**
 * Interface for an {@link EntityBean} having a {@link #Genre()}.
 */
@AbstractInterface
public abstract interface EntityWithGenre extends MusicalEntity {

  /**
   * @return the {@link Genre} of this entity.
   */
  LinkProperty<Genre> Genre();

}
