package io.github.musicdoc;

import java.util.Objects;

import io.github.musicdoc.stave.system.StaveSystem;

/**
 * Interface for a {@link MusicalObject} that is labeled with name and abbreviation.
 *
 * @param <SELF> type of this object itself.
 */
public abstract class AbstractMutableLabeledObject<SELF extends AbstractMutableLabeledObject<SELF>>
    extends AbstractMusicalObject implements MutableObject<SELF> {

  /** Property name of {@link #getName() name}. */
  public static final String PROPERTY_NAME = "name";

  /** Property name of {@link #getAbbreviation() abbreviation}. */
  public static final String PROPERTY_ABBREVIATION = "abbreviation";

  /** @see #getName() */
  protected String name;

  /** @see #getAbbreviation() */
  protected String abbreviation;

  /**
   * The constructor.
   */
  public AbstractMutableLabeledObject() {

    super();
    this.name = "";
    this.abbreviation = "";
  }

  /**
   * The copy-constructor.
   *
   * @param copy the {@link AbstractMutableLabeledObject} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AbstractMutableLabeledObject(AbstractMutableLabeledObject<SELF> copy, MutableObjecteCopier copier) {

    super();
    this.name = copy.name;
    this.abbreviation = copy.abbreviation;
  }

  /**
   * @return the display name (e.g. "Bass" or "Piano"). May be {@link String#isEmpty() empty} but never {@code null}. If
   *         not {@link String#isEmpty() empty} it will be printed as label to the left of the first occurrence of the
   *         {@link StaveSystem}.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param newName the new {@link #getName() name}.
   * @return this object with the given {@link #getName() name} and all other properties like {@code this} one. Will be
   *         a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public SELF setName(String newName) {

    if (newName == null) {
      newName = "";
    }
    newName = newName.trim();
    if (Objects.equals(this.name, newName)) {
      return self();
    }
    SELF voice = makeMutable();
    ((AbstractMutableLabeledObject<?>) voice).name = newName;
    return voice;
  }

  /**
   * @return the abbreviation of the {@link #getName() name}. ay be {@link String#isEmpty() empty} but never
   *         {@code null}. If not {@link String#isEmpty() empty} it will be printed as label to the left of every
   *         repeated {@link StaveSystem} to save space. This is called "subname" ("snm") in ABC notation.
   */
  public String getAbbreviation() {

    return this.abbreviation;
  }

  /**
   * @param newAbbreviation the new {@link #getAbbreviation() abbreviation}.
   * @return this object with the given {@link #getAbbreviation() abbreviation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public SELF setAbbreviation(String newAbbreviation) {

    if (newAbbreviation == null) {
      newAbbreviation = "";
    }
    newAbbreviation = newAbbreviation.trim();
    if (Objects.equals(this.abbreviation, newAbbreviation)) {
      return self();
    }
    SELF voice = makeMutable();
    ((AbstractMutableLabeledObject<?>) voice).abbreviation = newAbbreviation;
    return voice;
  }

}
