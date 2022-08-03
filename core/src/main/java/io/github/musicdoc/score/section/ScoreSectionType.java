package io.github.musicdoc.score.section;

/**
 * {@link Enum} with the {@link ScoreSectionName#getType() type}s of a {@link ScoreSectionName}.
 */
public enum ScoreSectionType {

  /** Type of the chorus. */
  CHORUS("Chorus", "C"),

  /** Type of a verse. */
  VERSE("Verse", "V"),

  /** Type of the bridge. */
  BRDIGE("Bridge", "B"),

  /** Type of the solo. */
  SOLO("SOLO", "S"),

  /** Type of the intro. */
  INTRO("INTRO", "I"),

  /** Type of the outro. */
  OUTRO("OUTRO", "O");

  private final String name;

  private final String id;

  private ScoreSectionType(String name, String id) {

    this.name = name;
    this.id = id;
  }

  /**
   * @return the readable name of this type in English language.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the short identifier of this type (typically a single character).
   */
  public String getId() {

    return this.id;
  }

  @Override
  public String toString() {

    return this.name;
  }
}
