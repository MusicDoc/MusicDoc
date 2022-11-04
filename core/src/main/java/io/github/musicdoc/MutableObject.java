package io.github.musicdoc;

/**
 * Interface for a potentially mutable object. Unlike a regular Java bean implementations of this interface can either
 * be {@link #isMutable() mutable} or {@link #isImmutable() immutable}. If it is {@link #isImmutable() immutable}, it is
 * read-only and none of its properties can be modified anymore. Therefore all setter methods always return a object of
 * the type itself (here {@code <T>}). If the object is {@link #isMutable() mutable} the setter will simply change the
 * property like a regular setter and return itself ({@code this}) as a result. However, if it is {@link #isImmutable()
 * immutable}, the setter will create a copy of the object with the new property value set and return the copy instead.
 * That copy will be {@link #isMutable() mutable} again.<br>
 * In order to make an object {@link #isImmutable() immutable} or to ensure that it is, you can simply call
 * {@link #makeImmutable()}. To ensure having a {@link #isMutable() mutable} instance (copy if {@link #isImmutable()
 * immutable} or the same instance if already {@link #isMutable() mutable}), simply call {@link #makeMutable()}.</br>
 * So with a regular Java bean it typically looks like this:
 *
 * <pre>
 * MyBean bean = new MyBean();
 * bean.setFirstName("First");
 * bean.setLastName("Last");
 * </pre>
 *
 * However, with a {@link MutableObject} it more looks like this:
 *
 * <pre>
 * MyMutable mutable = new MyMutable().setFirstName("First").setLastName("Last").makeImmutable();
 *
 * MyMutable copy = mutable.setFirstName("James");
 * </pre>
 *
 * In the last example {@code mutable} will not be modified, instead {@code copy} will be a new instance with "James" as
 * first name and "Last" as last name. <br>
 * To make this work all you need to do in your setter implementation is to write it like this:
 *
 * <pre>
 * public MyMutable setFirstName(String newFirstName) {
 *
 *   MyMutable copy = makeMutable();
 *   copy.firstName = newFirstName;
 *   return copy;
 * }
 * </pre>
 *
 * @param <SELF> type of this object itself.
 */
public interface MutableObject<SELF extends MutableObject<SELF>> extends MusicalObject {

  /**
   * @return {@code true} if immutable (unmodifiable), {@code false} otherwise (if {@link #isMutable() mutable}).
   */
  boolean isImmutable();

  /**
   * @return {@code true} if mutable (modifiable), {@code false} otherwise (if {@link #isImmutable() immutable}).
   */
  default boolean isMutable() {

    return !isImmutable();
  }

  /**
   * Makes this object {@link #isImmutable() immutable}. This is an irreversible operation that can not be undone.
   * Subsequent calls of this method will have no further object.
   *
   * @return {@code this} object itself for fluent API calls.
   */
  SELF makeImmutable();

  /**
   * Ensures that this object is mutable.
   *
   * @throws IllegalStateException if {@link #isImmutable() immutable}.
   */
  default void requireMutable() {

    if (isImmutable()) {
      throw new IllegalStateException("Object is immutable and can not be modified: " + this);
    }
  }

  /**
   * @return {@code this} object itself.
   */
  @SuppressWarnings("unchecked")
  default SELF self() {

    return (SELF) this;
  }

  /**
   * @return a {@link #isMutable() mutable} copy of this object.
   */
  default SELF copy() {

    return copy(MutableObjecteCopierSimple.INSTANCE);
  }

  /**
   * @param copier the {@link MutableObjecteCopier}.
   * @return a {@link #isMutable() mutable} copy of this object.
   */
  SELF copy(MutableObjecteCopier copier);

  /**
   * @return {@code this} object itself if {@link #isMutable()} or a {@link #copy()} otherwise.
   */
  default SELF makeMutable() {

    if (isImmutable()) {
      return copy();
    } else {
      return self();
    }
  }

}
