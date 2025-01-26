package io.github.musicdoc.genre;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.entity.id.Id;
import io.github.mmm.entity.id.RevisionedIdVersion;

/**
 * Test of {@link Genre}.
 */
public class GenreTest extends Assertions {

  @Test
  public void testGenre() {

    Genre genre = Genre.of();
    Id<?> id = genre.Id().get();
    assertThat(id).isInstanceOf(RevisionedIdVersion.class);
    assertThat(id.getEntityClass()).isEqualTo(Genre.class);
    assertThat(id.get()).isNull();
    assertThat(id.getRevision()).isNull();
    genre.Title().set("Pop");
    assertThat(genre.Title().get()).isEqualTo("Pop");
  }

}
