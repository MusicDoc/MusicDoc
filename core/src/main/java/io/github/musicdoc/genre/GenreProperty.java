package io.github.musicdoc.genre;

import io.github.mmm.property.Property;
import io.github.musicdoc.search.SearchScore;
import io.github.musicdoc.search.SearchableStringProperty;

/**
 * {@link Property} with {@link MusicalGenre} as {@link #get() value}.
 */
public class GenreProperty extends SearchableStringProperty {

  /** Default {@link #getName() name}. */
  public static final String NAME = "Genre";

  /**
   * The constructor.
   */
  public GenreProperty() {

    super(NAME, SearchScore.AVG, null, null);
  }

  /**
   * @param repository the {@link GenreRepository}.
   * @return the {@link #get() value} as {@link MusicalGenre}.
   */
  public MusicalGenre getAsGenre(GenreRepository repository) {

    String genreName = get();
    if (genreName == null) {
      return MusicalGenres.MUSIC;
    }
    MusicalGenre genre = repository.getOrCreate(genreName);
    return genre;
  }

  /**
   * @param genre the {@link MusicalGenre} to set.
   */
  public void setAsGenre(MusicalGenre genre) {

    if (genre == null) {
      set(null);
    } else {
      set(genre.getName());
    }
  }
}
