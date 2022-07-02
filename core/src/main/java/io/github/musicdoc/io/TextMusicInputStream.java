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
public class TextMusicInputStream extends AbstractTextMusicInputStream implements AttributePropertySuffix {

  private static final CharFilter FILTER_PROPERTY_CHAR = ListCharFilter.LETTERS_AND_DIGITS.join(' ', '_', '-');;

  private String currentProperty;

  private String propertyInfix;

  private String propertySuffix;

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   */
  public TextMusicInputStream(String string) {

    this(string, 0);
  }

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   */
  public TextMusicInputStream(String string, int index) {

    this(string, index, string.length() - 1);
  }

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   * @param end the end position where to stop parsing. Typically the index of the last character in the
   *        {@link #getString() string to parse}.
   */
  public TextMusicInputStream(String string, int index, int end) {

    super(string, index, end);
    this.propertyInfix = PROPERTIES_KEY_VALUE_SEPARATOR;
    this.propertySuffix = NEWLINE;
  }

  @Override
  public String readPropertyStart() {

    if (this.currentProperty != null) {
      throw new IllegalStateException("Previous property (" + this.currentProperty + ") has not been terminated.");
    }
    if (!hasNext()) {
      return null;
    }
    int infixPos = this.string.indexOf(this.propertyInfix, this.index);
    if (infixPos <= this.index) {
      return null;
    }
    for (int i = this.index; i < infixPos; i++) {
      char c = this.string.charAt(i);
      if (!FILTER_PROPERTY_CHAR.accept(c)) {
        return null;
      }
    }
    this.currentProperty = this.string.substring(this.index, infixPos);
    this.column += (infixPos - this.index);
    this.index = infixPos + 1;
    return this.currentProperty;
  }

  @Override
  public String readPropertyValue() {

    if (this.currentProperty == null) {
      throw new IllegalStateException("Parser not inside property.");
    }
    boolean newline = NEWLINE.equals(this.propertySuffix);
    int newIndex = this.index;
    if (!hasNext()) {
      addWarning("Property (" + this.currentProperty + ") not terminated.");
      return null;
    }
    int endIndex = this.string.indexOf(this.propertySuffix, this.index);
    int max = endIndex;
    if (endIndex < 0) {
      max = this.end;
    } else {
      newIndex = endIndex + this.propertySuffix.length();
    }
    if (!newline) {
      int i = this.index;
      while (i < max) {
        char c = this.string.charAt(i);
        if ((c == STAVE_END) || (c == NEWLINE_CHAR) || (c == END_BLOCK)) {
          endIndex = i;
          newIndex = i;
          newline = (c == NEWLINE_CHAR);
          break;
        }
        i++;
      }
    }
    if (endIndex < 0) {
      addWarning("Property (" + this.currentProperty + ") not terminated.");
      this.currentProperty = null;
      return null;
    }
    this.currentProperty = null;

    String value = this.string.substring(this.index, endIndex);
    if (newline) {
      this.line++;
      this.column = 1;
    } else {
      this.column += (newIndex - this.index);
    }
    this.index = newIndex;
    return value;
  }

  @Override
  public String getPropertySuffix() {

    return this.propertySuffix;
  }

  @Override
  public void setPropertySuffix(String propertySuffix) {

    this.propertySuffix = propertySuffix;
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
      return new TextMusicInputStream(sb.toString());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
