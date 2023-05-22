package io.github.musicdoc.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.FormatConstants;

/**
 * Implementation of {@link MusicInputStream} for plain {@link String}.
 */
public class TextMusicInputStream extends AbstractTextMusicInputStream {

  private static final CharFilter FILTER_PROPERTY_CHAR = CharFilter.LATIN_LETTER_OR_DIGIT
      .compose(new ListCharFilter("_-"));

  private final PropertyState rootProperty;

  private PropertyState currentProperty;

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param property the {@link PropertyState}.
   */
  public TextMusicInputStream(String string, PropertyState property) {

    this(string, 0, property);
  }

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   * @param property the {@link PropertyState}.
   */
  public TextMusicInputStream(String string, int index, PropertyState property) {

    this(string, index, string.length() - 1, property);
  }

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   * @param end the end position where to stop parsing. Typically the index of the last character in the
   *        {@link #getString() string to parse}.
   * @param property the {@link PropertyState}.
   */
  public TextMusicInputStream(String string, int index, int end, PropertyState property) {

    super(string, index, end);
    this.rootProperty = property;
    this.currentProperty = property;
  }

  @Override
  public boolean isPropertyStart(String property) {

    PropertyState state = this.currentProperty;
    if (this.currentProperty.currentName != null) {
      state = this.currentProperty.child;
    }
    if (state == null) {
      return false;
    }
    if ((this.scanner.peek(property.length()) == state.infix) && this.scanner.expect(property, false, true)) {
      return true;
    }
    return false;
  }

  @Override
  public String readPropertyStart() {

    PropertyState property = this.currentProperty;
    if (this.currentProperty.currentName != null) {
      property = this.currentProperty.child;
    }
    if (property == null) {
      throw new IllegalStateException("Previous property (" + this.rootProperty + ") has not been terminated.");
    }
    int i = 0;
    char c;
    while ((c = this.scanner.peek(i)) != CharStreamScanner.EOS) {
      if ((i == 0) && property.acceptMultipleSuffix && (c == property.suffix)) {
        this.scanner.next(); // skip suffix as prefix
      } else if (c == property.infix) {
        property.currentName = this.scanner.read(i);
        this.scanner.next(); // skip infix
        c = this.scanner.peek();
        if (c == '"') {
          property.currentQuoted = true;
          this.scanner.next(); // skip quote
        }
        this.currentProperty = property;
        return property.currentName;
      } else if (FILTER_PROPERTY_CHAR.accept(c)) {
        i++;
      } else {
        // property name may only contain letters, digits, etc.
        return null;
      }
    }
    // infix not found
    return null;
  }

  @Override
  public String readPropertyValue() {

    PropertyState property = this.currentProperty;
    if (this.currentProperty.currentName == null) {
      property = this.currentProperty.parent;
    }
    if (property == null) {
      throw new IllegalStateException("Parser not inside property.");
    }
    if (!this.scanner.hasNext()) {
      property.currentName = null;
      this.currentProperty = property;
      return "";
      // addWarning("Property (" + this.rootProperty + ") not terminated.");
      // return null;
    }
    boolean todo = true;
    String value = null;
    StringBuilder valueBuilder = null;
    while (todo) {
      if (property.currentQuoted) {
        value = this.scanner.readUntil('"', false, '\\');
        this.scanner.expectOne(property.suffix);
      } else {
        value = this.scanner.readUntil(property.suffix, true);
      }
      if (valueBuilder != null) {
        valueBuilder.append(value);
      }
      property.currentQuoted = false;
      todo = false;
      if ((property.continuation != null) && this.scanner.expect(property.continuation, false)) {
        todo = true;
        if (valueBuilder == null) {
          valueBuilder = new StringBuilder(value);
        }
        if (this.scanner.expectOne('"')) {
          property.currentQuoted = true;
        }
      }
    }
    property.currentName = null;
    this.currentProperty = property;
    if (valueBuilder != null) {
      value = valueBuilder.toString();
    }
    return value;
  }

  @Override
  public void close() {

    // nothing to do
  }

  /**
   * @param inStream the {@link InputStream} to read from.
   * @return the {@link TextMusicInputStream} reading from the given {@link InputStream}.
   */
  public static TextMusicInputStream of(InputStream inStream) {

    return of(inStream, PropertyState.of());
  }

  /**
   * @param inStream the {@link InputStream} to read from.
   * @param propertyState the new {@link PropertyState} to use.
   * @return the {@link TextMusicInputStream} reading from the given {@link InputStream}.
   */
  public static TextMusicInputStream of(InputStream inStream, PropertyState propertyState) {

    try (InputStreamReader isr = new InputStreamReader(inStream, ENCODING);
        BufferedReader reader = new BufferedReader(isr)) {
      StringBuilder sb = new StringBuilder(2048);
      String line = null;
      do {
        line = reader.readLine();
        if (line != null) {
          if (sb.length() > 0) {
            sb.append(FormatConstants.NEWLINE);
          }
          sb.append(line);
        }
      } while (line != null);
      return new TextMusicInputStream(sb.toString(), propertyState);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
