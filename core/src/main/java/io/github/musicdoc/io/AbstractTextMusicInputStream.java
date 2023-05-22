package io.github.musicdoc.io;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.scanner.CharSequenceScanner;
import io.github.mmm.scanner.CharStreamScanner;

/**
 * Abstract base implementation of {@link MusicInputStream} for plain {@link String}.
 */
public abstract class AbstractTextMusicInputStream extends AbstractMusicStream implements MusicInputStream {

  /** @see #getScanner() */
  protected CharStreamScanner scanner;

  /**
   * The constructor.
   *
   * @param string the {@link String} to parse.
   * @param index the offset where to start parsing. Typically {@code 0} to start from the beginning.
   * @param end the end position where to stop parsing. Typically the index of the last character in the {@link String}
   *        to parse.
   */
  public AbstractTextMusicInputStream(String string, int index, int end) {

    super();
    setString(string, index, end);
  }

  @Override
  public CharStreamScanner getScanner() {

    return this.scanner;
  }

  /**
   * @param string the {@link String} to parse.
   */
  protected void setString(String string) {

    setString(string, 0, string.length() - 1);
  }

  /**
   * @param string the {@link String} to parse.
   * @param index the current start index with the {@link String}.
   * @param end the maximum index where to read from {@link String}.
   */
  protected void setString(String string, int index, int end) {

    assert (end < string.length());
    this.scanner = new CharSequenceScanner(string);
  }

  @Override
  public int getLine(boolean relative) {

    return this.scanner.getLine();
  }

  @Override
  public int getColumn(boolean relative) {

    return this.scanner.getColumn();
  }

  @Override
  public Integer readInteger(int maxLen, boolean acceptSign) {

    if ((maxLen > 11) || (maxLen <= 0)) {
      throw new IllegalArgumentException("" + maxLen);
    }
    int len = 0;
    while (len < maxLen) {
      char c = this.scanner.peek(len);
      if (CharFilter.LATIN_DIGIT.accept(c)) {
        len++;
      } else if (acceptSign && (len == 0) && ((c == '+') || (c == '-'))) {
        len++;
      } else {
        break;
      }
    }
    if (len == 0) {
      return null;
    }
    String integer = this.scanner.read(len);
    return Integer.valueOf(integer);
  }

  @Override
  public String toString() {

    return this.scanner.toString();
  }

}
