package io.github.musicdoc.io;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;

/**
 * Abstract base implementation of {@link MusicInputStream} for plain {@link String}.
 */
public abstract class AbstractTextMusicInputStream extends AbstractMusicStream implements MusicInputStream {

  /** @see #getString() */
  protected String string;

  /** The maximum index in {@link #string} to read from. */
  protected int end;

  /** @see #getIndex() */
  protected int index;

  /** @see #getLine() */
  protected int line;

  /** @see #getColumn() */
  protected int column;

  /**
   * The constructor.
   *
   * @param string the {@link #getString() string to parse}.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   * @param end the end position where to stop parsing. Typically the index of the last character in the
   *        {@link #getString() string to parse}.
   */
  public AbstractTextMusicInputStream(String string, int index, int end) {

    super();
    setString(string, index, end);
  }

  /**
   * @param string the {@link #getString() string} to parse.
   */
  protected void setString(String string) {

    setString(string, 0, string.length() - 1);
  }

  /**
   * @param string the {@link #getString() string} to parse.
   * @param index the current start index with the {@link String}.
   * @param end the maximum index where to read from {@link String}.
   */
  protected void setString(String string, int index, int end) {

    assert (end < string.length());
    this.string = string;
    this.end = end;
    this.index = index;
    this.line = 1;
    this.column = 1;
  }

  @Override
  public long getIndex() {

    return this.index;
  }

  @Override
  public int getLine(boolean relative) {

    return this.line;
  }

  @Override
  public int getColumn(boolean relative) {

    return this.column;
  }

  @Override
  public boolean hasNext() {

    return (this.index <= this.end);
  }

  @Override
  public char next() {

    if (this.index <= this.end) {
      char c = this.string.charAt(this.index++);
      if ((c == '\r') && (this.index <= this.end)) {
        c = this.string.charAt(this.index);
        if (c == '\n') {
          this.index++;
        } else {
          c = '\r'; // broken file?
        }
      }
      processChar(c);
      return c;
    }
    return 0;
  }

  private void processChar(char c) {

    if (c == '\n') {
      this.line++;
      this.column = 1;
    } else {
      this.column++;
    }
  }

  @Override
  public char peek() {

    if (this.index <= this.end) {
      return this.string.charAt(this.index);
    }
    return 0;
  }

  @Override
  public String peek(int maxLen) {

    int max = this.index + maxLen;
    if (max > this.end) {
      max = this.end + 1;
    }
    if (this.index >= max) {
      return "";
    }
    return this.string.substring(this.index, max);
  }

  @Override
  public String peekUntil(CharFilter stopFilter, int maxLen) {

    if (this.index > this.end) {
      return "";
    }
    int i = this.index;
    int maxEnd = this.index + maxLen;
    if (maxEnd > this.end) {
      maxEnd = this.end;
    }
    while ((i <= maxEnd) && !stopFilter.accept(this.string.charAt(i))) {
      i++;
    }
    if (i > this.index) {
      return this.string.substring(this.index, i);
    } else {
      return "";
    }
  }

  @Override
  public String peekWhile(CharFilter filter, int maxLen) {

    if (this.index > this.end) {
      return "";
    }
    int i = this.index;
    int maxEnd = this.index + maxLen;
    if (maxEnd > this.end) {
      maxEnd = this.end;
    }
    while ((i <= maxEnd) && filter.accept(this.string.charAt(i))) {
      i++;
    }
    if (i > this.index) {
      return this.string.substring(this.index, i);
    } else {
      return "";
    }
  }

  @Override
  public String read(int length) {

    int max = this.index + length;
    if ((max > this.end) || (length == Integer.MAX_VALUE)) {
      max = this.end + 1;
    }
    return readTo(max);
  }

  private String readTo(int endIndex) {

    String result = "";
    if (this.index < endIndex) {
      result = this.string.substring(this.index, endIndex);
    }
    int lastNewline = result.lastIndexOf('\n');
    if (lastNewline >= 0) {
      this.line++;
      this.column = result.length() - lastNewline;
      int newlineIndex = result.indexOf('\n');
      while ((newlineIndex >= 0) && (newlineIndex < lastNewline)) {
        this.line++;
        newlineIndex = result.indexOf('\n', newlineIndex + 1);
      }
    } else {
      this.column += result.length();
    }
    this.index = endIndex;
    return result;
  }

  @Override
  public String readUntil(char stop, boolean acceptEot) {

    if (this.index > this.end) {
      return "";
    }
    boolean found = false;
    int newIndex = this.index;
    while (newIndex <= this.end) {
      if (this.string.charAt(newIndex) == stop) {
        found = true;
        break;
      }
      newIndex++;
    }
    if (found || acceptEot) {
      return readTo(newIndex);
    } else {
      return null;
    }
  }

  @Override
  public String readUntil(CharFilter stopFilter, boolean acceptEot) {

    if (this.index > this.end) {
      return "";
    }
    int newIndex = this.index;
    boolean found = false;
    while (newIndex <= this.end) {
      if (stopFilter.accept(this.string.charAt(newIndex))) {
        found = true;
        break;
      }
      newIndex++;
    }
    if (found || acceptEot) {
      return readTo(newIndex);
    } else {
      return null;
    }
  }

  @Override
  public String readUntil(CharFilter stopFilter, int maxLength) {

    if ((this.index > this.end) || (maxLength <= 0)) {
      return "";
    }
    int newIndex = this.index;
    int maxIndex = this.index + maxLength - 1;
    if ((maxIndex >= this.end) || (maxIndex < this.index)) {
      maxIndex = this.end;
    }
    while (newIndex <= maxIndex) {
      if (stopFilter.accept(this.string.charAt(newIndex))) {
        break;
      }
      newIndex++;
    }
    return readTo(newIndex);
  }

  @Override
  public String readWhile(CharFilter filter) {

    if (this.index > this.end) {
      return "";
    }
    int newIndex = this.index;
    while (newIndex <= this.end) {
      if (!filter.accept(this.string.charAt(newIndex))) {
        break;
      }
      newIndex++;
    }
    return readTo(newIndex);
  }

  @Override
  public Integer readInteger() {

    return readInteger(11, true);
  }

  @Override
  public Integer readInteger(int maxLen, boolean acceptSign) {

    if ((maxLen > 11) || (maxLen <= 0)) {
      throw new IllegalArgumentException("" + maxLen);
    }
    String integer = readIntegerString(maxLen, acceptSign);
    if (integer == null) {
      return null;
    }
    return Integer.valueOf(integer);
  }

  private String readIntegerString(int maxLength, boolean acceptSign) {

    int newIndex = this.index;
    int max = this.index + maxLength;
    if (max > this.end) {
      max = this.end;
    }
    boolean hasDigit = false;
    while (newIndex <= max) {
      char c = this.string.charAt(newIndex);
      if (ListCharFilter.DIGITS.accept(c)) {
        hasDigit = true;
      } else if (!acceptSign || hasDigit || ((c != '-') && (c != '+'))) {
        break;
      }
      newIndex++;
    }
    if (!hasDigit) {
      return null;
    }
    String number = this.string.substring(this.index, newIndex);
    this.index = newIndex;
    this.column += number.length();
    return number;
  }

  @Override
  public int skip(int length) {

    int newIndex = this.index + length;
    int skipped = length;
    if ((newIndex > this.end) || (newIndex < 0)) {
      newIndex = this.end + 1;
      skipped = newIndex - this.index;
    }
    while (this.index < newIndex) {
      char c = this.string.charAt(this.index++);
      processChar(c);
    }
    return skipped;
  }

  @Override
  public int skipWhile(char skip) {

    int start = this.index;
    while ((this.index <= this.end) && (this.string.charAt(this.index) == skip)) {
      this.index++;
      processChar(skip);
    }
    return this.index - start;
  }

  @Override
  public int skipWhile(CharFilter filter) {

    int start = this.index;
    while (this.index <= this.end) {
      char c = this.string.charAt(this.index);
      if (!filter.accept(c)) {
        break;
      }
      processChar(c);
      this.index++;
    }
    return this.index - start;
  }

  @Override
  public int skipUntil(CharFilter stopFilter) {

    int start = this.index;
    while (this.index <= this.end) {
      char c = this.string.charAt(this.index);
      if (stopFilter.accept(c)) {
        break;
      }
      processChar(c);
      this.index++;
    }
    return this.index - start;
  }

  @Override
  public boolean skipNewline() {

    if (this.index > this.end) {
      return false;
    }
    char c = this.string.charAt(this.index);
    if (c == '\n') {
      this.index++;
      if ((this.index <= this.end) && (this.string.charAt(this.index) == '\r')) {
        this.index++;
      }
      this.line++;
      this.column = 1;
      return true;
    } else if (c == '\r') {
      this.index++;
      if ((this.index <= this.end) && (this.string.charAt(this.index) == '\n')) {
        this.index++;
      }
      this.line++;
      this.column = 1;
      return true;
    }
    return false;
  }

  @Override
  public boolean expect(char expected) {

    return expect(expected, false);
  }

  @Override
  public boolean expect(char expected, boolean warning) {

    boolean found = false;
    if (this.index <= this.end) {
      char c = this.string.charAt(this.index);
      if (c == expected) {
        found = true;
        this.index++;
        processChar(c);
      }
    }
    if (warning && !found) {
      addWarning("Expected '" + expected + "'");
    }
    return found;
  }

  @Override
  public boolean expect(String expected, boolean ignoreCase) {

    int expectedLength = expected.length();
    if ((this.end + 1 - this.index) < expectedLength) {
      return false;
    }
    int newIndex = this.index;
    int lineNew = this.line;
    int columnNew = this.column;
    for (int expectedIndex = 0; expectedIndex < expectedLength; expectedIndex++) {
      if (newIndex > this.end) {
        return false;
      }
      char c = this.string.charAt(newIndex);
      char exp = expected.charAt(expectedIndex);
      if (c != exp) {
        if ((!ignoreCase) || (Character.toLowerCase(c) != Character.toLowerCase(exp))) {
          return false;
        }
      }
      if (c == '\n') {
        lineNew++;
        columnNew = 1;
      } else {
        columnNew++;
      }
      newIndex++;
    }
    this.index = newIndex;
    this.line = lineNew;
    this.column = columnNew;
    return true;
  }

  /**
   * @return the entire {@link String} to parse.
   */
  public String getString() {

    return this.string;
  }

  @Override
  public String toString() {

    if (this.index > this.end) {
      return "";
    }
    return this.string.substring(this.index);
  }

}
