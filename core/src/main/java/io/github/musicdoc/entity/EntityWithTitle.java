package io.github.musicdoc.entity;

import io.github.mmm.bean.AbstractInterface;
import io.github.mmm.entity.bean.EntityBean;
import io.github.mmm.property.string.StringProperty;

/**
 * Interface for an {@link EntityBean} having a {@link #Title()}.
 */
@AbstractInterface
public abstract interface EntityWithTitle extends EntityBean {

  /**
   * @return the title (display name) of the entity (e.g. "Chiquitita" for a song, "Voulez-Vous" for an album, "ABBA"
   *         for an artist, or "Soft rock" for a genre).
   */
  StringProperty Title();

}
