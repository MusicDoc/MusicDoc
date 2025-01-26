package io.github.musicdoc.genre;

/**
 * Extends {@link MusicalGenresTest} to test with {@link MusicalGenres#of()}.
 */
public class MusicalGenresMutableTest extends MusicalGenresTest {

  private final GenreRepository repository;

  MusicalGenresMutableTest() {

    super();
    this.repository = MusicalGenres.of();
  }

  @Override
  protected GenreRepository getRepository() {

    return this.repository;
  }

  @Override
  protected boolean isMutable() {

    return true;
  }

}
