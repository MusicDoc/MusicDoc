package io.github.musicdoc.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

import io.github.musicdoc.format.TextPosition;

/**
 * Wrapper for {@link Appendable} that tracks the {@link TextPosition}.
 */
public class TextPositionAppendable extends Writer implements TextPosition {

  private final Appendable out;

  private int line;

  private int column;

  /**
   * The constructor.
   *
   * @param out the {@link Appendable} to wrap.
   */
  public TextPositionAppendable(Appendable out) {

    super();
    this.out = out;
    this.line = 1;
    this.column = 1;
  }

  @Override
  public int getLine() {

    return this.line;
  }

  @Override
  public int getColumn() {

    return this.column;
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for (int i = off; i < len; i++) {
      process(cbuf[i]);
    }
    this.out.append(new String(cbuf, off, len));
  }

  @Override
  public void write(int chr) throws IOException {

    char c = (char) chr;
    process(c);
    this.out.append(c);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    for (int i = off; i < len; i++) {
      process(str.charAt(i));
    }
    this.out.append(str, off, len);
  }

  private void process(char c) {

    if (c == '\n') {
      this.line++;
      this.column = 1;
    } else {
      this.column++;
    }
  }

  @Override
  public void flush() throws IOException {

    if (this.out instanceof Flushable) {
      ((Flushable) this.out).flush();
    }
  }

  @Override
  public void close() throws IOException {

    if (this.out instanceof Closeable) {
      ((Closeable) this.out).close();
    }
  }
}
