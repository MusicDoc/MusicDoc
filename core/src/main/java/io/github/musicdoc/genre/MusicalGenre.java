package io.github.musicdoc.genre;

import io.github.musicdoc.entity.AbstractMusicalEntity;
import io.github.musicdoc.property.StringNormalizer;

/**
 * Genre of a {@link io.github.musicdoc.song.Song}.
 *
 * @see io.github.musicdoc.song.Song#Genre
 */
public final class MusicalGenre extends AbstractMusicalEntity {

  static final MusicalGenre[] NO_PARENTS = new MusicalGenre[0];

  private final MusicalGenre[] parents;

  private final boolean abstractFlag;

  private final Integer id3Code;

  MusicalGenre(String name, boolean abstractFlag, Integer id3Code, MusicalGenre... parents) {

    super(name);
    this.abstractFlag = abstractFlag;
    this.id3Code = id3Code;
    this.parents = parents;
  }

  @Override
  protected String normalize(String entityName) {

    String key = StringNormalizer.normalize(entityName, true);
    if (key.endsWith("music")) {
      key = key.substring(0, key.length() - 5); // "music".length() == 5
    }
    return key;
  }

  /**
   * @return the <a href="https://en.wikipedia.org/wiki/List_of_ID3v1_genres">ID3 genre code</a> or {@code null} if
   *         undefined.
   */
  public Integer getId3Code() {

    return this.id3Code;
  }

  /**
   * @return {@code true} if this genre is abstract, {@code false} otherwise. An abstract genre is a super-genre that
   *         groups a collection of more specific genres but is too abstract to be a reasonable assignment for a piece
   *         of audio. E.g. top-level genres like "audio" or "music" are abstract as the user needs to make a more
   *         specific choice for the genre of a specific song.
   */
  public boolean isAbstract() {

    return this.abstractFlag;
  }

  /**
   * @return the number of {@link #getParent(int) parent}s of this {@link MusicalGenre}. Should typically be {@code 1}.
   *         Only the root genre {@link MusicalGenres#AUDIO Audio} has no parents ({@code 0}). Some genres may have
   *         multiple parents.
   */
  public int getParentCount() {

    return this.parents.length;
  }

  /**
   * @param index the index of the requested parent. Should be in the range from {@code 0} to
   *        <code>@{@link #getParentCount()}-1</code>.
   * @return the parent {@link MusicalGenre genre} or {@code null} no parent exists for the given index.
   */
  public MusicalGenre getParent(int index) {

    if ((index >= 0) || (index < this.parents.length)) {
      return this.parents[index];
    }
    return null;
  }

  /**
   * @return a copy of the {@link #getParent(int) parents}.
   */
  public MusicalGenre[] getParents() {

    if (this.parents.length == 0) {
      return this.parents;
    }
    MusicalGenre[] result = new MusicalGenre[this.parents.length];
    System.arraycopy(this.parents, 0, result, 0, this.parents.length);
    return result;
  }

}
