package io.github.musicdoc.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.music.format.FormatConstants;

/**
 * Implementation of {@link MusicInputStream} for plain {@link String}.
 */
public class TextMusicInputStream extends AbstractTextMusicInputStream {

  private static final CharFilter FILTER_PROPERTY_CHAR = ListCharFilter.LETTERS_AND_DIGITS.join('_', '-');;

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
    int len = property.length();
    int i = this.index + len;
    if ((i <= this.end) && (this.string.charAt(i) == state.infix)) {
      String p = this.string.substring(this.index, i);
      if (property.equals(p)) {
        return true;
      }
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
    if (!hasNext()) {
      return null;
    }
    int start = this.index;
    for (int i = this.index; i <= this.end; i++) {
      char c = this.string.charAt(i);
      if (property.acceptMultipleSuffix && (c == property.suffix) && (i == start)) {
        start++;
      } else if (c == property.infix) {
        property.currentName = this.string.substring(start, i);
        int newIndex = i + 1;
        if (newIndex <= this.end) {
          c = this.string.charAt(newIndex);
          if (c == '"') {
            property.currentQuoted = true;
            newIndex++;
          }
        }
        this.column += (newIndex - this.index);
        this.index = newIndex;
        this.currentProperty = property;
        return property.currentName;
      } else if (!FILTER_PROPERTY_CHAR.accept(c)) {
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
    if (!hasNext()) {
      property.currentName = null;
      this.currentProperty = property;
      return "";
      // addWarning("Property (" + this.rootProperty + ") not terminated.");
      // return null;
    }
    int nextLine = this.line;
    int nextColumn = this.column;
    int newIndex = this.index;
    int endIndex = -1;
    boolean escape = false;
    boolean escapedQuote = false;
    boolean todo = true;
    String value = null;
    StringBuilder valueBuilder = null;
    while (todo) {
      for (int i = this.index; i <= this.end; i++) {
        char c = this.string.charAt(i);
        if (c == NEWLINE_CHAR) {
          nextLine++;
          nextColumn = 1;
        } else if (c != '\r') {
          nextColumn++;
        }
        if (c == property.suffix) {
          endIndex = i;
          newIndex = i + 1;
          while (property.isAcceptMultipleSuffix() && (newIndex <= this.end)
              && (this.string.charAt(newIndex) == property.suffix)) {
            newIndex++;
          }
          break;
        } else if (property.isParentSuffix(c)) {
          endIndex = i;
          newIndex = i;
          break;
        } else if (property.currentQuoted && (c == '"')) {
          if (escape) {
            escapedQuote = true;
          } else {
            endIndex = i;
            newIndex = i + 1;
            if ((newIndex <= this.end) && (this.string.charAt(newIndex) == property.suffix)) {
              newIndex++;
            }
            break;
          }
        } else if (i == this.end) {
          endIndex = i + 1;
          newIndex = i + 1;
          break;
        }
        escape = (c == '\\');
      }
      if (valueBuilder == null) {
        value = this.string.substring(this.index, endIndex);
        if (escapedQuote) {
          value = value.replace("\\\"", "\"");
        }
      } else {
        if (escapedQuote) {
          value = this.string.substring(this.index, endIndex);
          value = value.replace("\\\"", "\"");
          valueBuilder.append(value);
        } else {
          valueBuilder.append(this.string, this.index, endIndex);
        }
      }
      this.index = newIndex;
      property.currentQuoted = false;
      todo = false;
      if ((property.continuation != null) && expect(property.continuation, false)) {
        todo = true;
        escapedQuote = false;
        if (valueBuilder == null) {
          valueBuilder = new StringBuilder(value);
        }
        if (expect('"')) {
          property.currentQuoted = true;
        }
      }
    }
    this.line = nextLine;
    this.column = nextColumn;
    property.currentName = null;
    this.currentProperty = property;
    if (valueBuilder != null) {
      value = valueBuilder.toString();
    }
    return value;
  }

  @Override
  public void close() {

    this.index = this.end;
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
