package io.github.musicdoc.music.score.section;

import io.github.musicdoc.AbstractMusicalObject;

/**
 * Represents the name of a {@link ScoreSection} in a structured way with {@link #getType() type} and
 * {@link #getSuffix() suffix}.
 *
 * @see ScoreSection#getName()
 */
public class ScoreSectionName extends AbstractMusicalObject {

  private final String name;

  private final ScoreSectionType type;

  private final boolean typeId;

  private final String suffix;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public ScoreSectionName(String name) {

    super();
    this.name = name;
    String newSuffix = null;
    boolean newTypeId = false;
    ScoreSectionType newType = null;
    for (ScoreSectionType t : ScoreSectionType.values()) {
      newSuffix = getSuffix(name, t.getId());
      if (newSuffix == null) {
        newSuffix = getSuffix(name, t.getName());
      } else {
        newTypeId = true;
      }
      if (newSuffix != null) {
        newType = t;
        break;
      }
    }
    this.type = newType;
    if (newSuffix == null) {
      this.suffix = "";
    } else {
      this.suffix = newSuffix;
    }
    this.typeId = newTypeId;
  }

  private static String getSuffix(String name, String type) {

    if (name.startsWith(type)) {
      String suffix = name.substring(type.length());
      if (suffix.isEmpty()) {
        return suffix;
      }
      char c = suffix.charAt(0);
      if ((c == ' ') || ((c >= '0') && (c <= '9'))) {
        return suffix;
      }
    }
    return null;
  }

  /**
   * @return the raw name of this section (including type and suffix).
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the standard {@link ScoreSectionType} or {@code null} if no type was detected.
   */
  public ScoreSectionType getType() {

    return this.type;
  }

  /**
   * @return {@code true} if the {@link #getType() type} was detected via its {@link ScoreSectionType#getId() ID},
   *         {@code false} otherwise.
   */
  public boolean isTypeId() {

    return this.typeId;
  }

  /**
   * @return the optional suffix (e.g. "1" if name is "Verse1").
   */
  public String getSuffix() {

    return this.suffix;
  }

  @Override
  public String toString() {

    return this.name;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append("#[");
    sb.append(this.name);
    sb.append(']');
  }
}
