package io.github.musicdoc.io;

import java.util.List;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.text.TextFormatMessage;
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
   * @param string the data to parse as {@link String}.
   */
  public AbstractTextMusicInputStream(String string) {

    super();
    setString(string);
  }

  /**
   * The constructor.
   *
   * @param scanner the {@link CharStreamScanner} with the data to parse.
   */
  public AbstractTextMusicInputStream(CharStreamScanner scanner) {

    super();
    this.scanner = scanner;
  }

  @Override
  public CharStreamScanner getScanner() {

    return this.scanner;
  }

  @Override
  public void addMessage(TextFormatMessage message) {

    this.scanner.addMessage(message);
  }

  @Override
  public List<TextFormatMessage> getMessages() {

    return this.scanner.getMessages();
  }

  /**
   * @param string the {@link String} to parse.
   */
  protected void setString(String string) {

    this.scanner = new CharSequenceScanner(string);
  }

  @Override
  public int getLine() {

    return this.scanner.getLine();
  }

  @Override
  public int getColumn() {

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
