package io.github.musicdoc.io;

/**
 * State of property for textual format.
 */
public final class PropertyState {

  final char infix;

  final char suffix;

  final boolean acceptMultipleSuffix;

  final String continuation;

  final PropertyState child;

  PropertyState parent;

  String currentName;

  boolean currentQuoted;

  /**
   * The constructor.
   *
   * @param infix the {@link #getInfix() infix}.
   * @param suffix the {@link #getSuffix() suffix}.
   * @param acceptMultipleSuffix the {@link #isAcceptMultipleSuffix() acceptMultipleSuffix} flag.
   * @param continuation the {@link #getContinuation() continuation}.
   */
  public PropertyState(char infix, char suffix, boolean acceptMultipleSuffix, String continuation) {

    this(infix, suffix, acceptMultipleSuffix, continuation, null);
  }

  /**
   * The constructor.
   *
   * @param infix the {@link #getInfix() infix}.
   * @param suffix the {@link #getSuffix() suffix}.
   * @param acceptMultipleSuffix the {@link #isAcceptMultipleSuffix() acceptMultipleSuffix} flag.
   * @param continuation the {@link #getContinuation() continuation}.
   * @param child the {@link #getChild() child}.
   */
  public PropertyState(char infix, char suffix, boolean acceptMultipleSuffix, String continuation, PropertyState child) {

    super();
    this.infix = infix;
    this.suffix = suffix;
    this.acceptMultipleSuffix = acceptMultipleSuffix;
    this.continuation = continuation;
    this.child = child;
    if (child != null) {
      child.parent = this;
    }
  }

  /**
   * @return the infix that separates the property name (key) from its value (e.g. ":" or "=").
   */
  public char getInfix() {

    return this.infix;
  }

  /**
   * @return the suffix that terminates the property value (e.g. "\n" or " ").
   */
  public char getSuffix() {

    return this.suffix;
  }

  /**
   * @return {@code true} if multiple occurrences of {@link #getSuffix()} shall be accepted (they will be skipped
   *         automatically also as prefix), {@code false} otherwise (default).
   */
  public boolean isAcceptMultipleSuffix() {

    return this.acceptMultipleSuffix;
  }

  /**
   * @return the property continuation {@link String} (e.g. "+:" for ABC) or {@code null} for no continuation.
   */
  public String getContinuation() {

    return this.continuation;
  }

  /**
   * @return the optional child {@link PropertyState} or {@code null} for no child.
   */
  public PropertyState getChild() {

    return this.child;
  }

  boolean isParentSuffix(char c) {

    PropertyState p = this.parent;
    while (p != null) {
      if (p.suffix == c) {
        return true;
      }
      p = p.parent;
    }
    return false;
  }

  private String getPropertyPath() {

    StringBuffer sb = new StringBuffer();
    PropertyState current = this;
    while (current != null) {
      if (current.currentName != null) {
        if (sb.length() > 0) {
          sb.append('/');
        }
        sb.append(current.currentName);
      }
      current = current.child;
    }
    return sb.toString();
  }

  @Override
  public String toString() {

    return getPropertyPath();
  }

  /**
   * @return a new instance of {@link PropertyState} with the defaults.
   */
  public static PropertyState of() {

    return of(null);
  }

  /**
   * @param continuation the {@link #getContinuation() continuation}.
   * @return a new instance of {@link PropertyState} with the defaults and the specified {@link #getContinuation()
   *         continuation}.
   */
  public static PropertyState of(String continuation) {

    return new PropertyState(TextMusicInputStream.PROPERTIES_KEY_VALUE_SEPARATOR_CHAR, TextMusicInputStream.NEWLINE_CHAR, false,
        continuation, new PropertyState('=', ' ', true, null));
  }
}