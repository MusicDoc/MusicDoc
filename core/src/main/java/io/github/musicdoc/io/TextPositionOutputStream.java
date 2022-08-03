package io.github.musicdoc.io;

import java.io.IOException;
import java.io.OutputStream;

import io.github.musicdoc.format.TextPosition;

/**
 * Wrapper for {@link OutputStream} that tracks the {@link TextPosition}.
 */
public class TextPositionOutputStream extends OutputStream implements TextPosition {

  private final OutputStream out;

  private int line;

  private int column;

  /**
   * The constructor.
   *
   * @param out the {@link OutputStream} to wrap.
   */
  public TextPositionOutputStream(OutputStream out) {

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
  public void write(byte[] b, int off, int len) throws IOException {

    for (int i = off; i < len; i++) {
      process(b[i]);
    }
    this.out.write(b, off, len);
  }

  @Override
  public void write(byte[] data) throws IOException {

    for (byte b : data) {
      process(b);
    }
    this.out.write(data);
  }

  @Override
  public void write(int b) throws IOException {

    process(b);
    this.out.write(b);
  }

  private void process(int b) {

    if (b == '\n') {
      this.line++;
      this.column = 1;
    } else {
      this.column++;
    }
  }

  @Override
  public void flush() throws IOException {

    this.out.flush();
  }

  @Override
  public void close() throws IOException {

    this.out.close();
  }
}
