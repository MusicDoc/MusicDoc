package io.github.musicdoc.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * {@link MusicOutputStream} for simple text based formats such as MusicDoc or ABC.
 */
public class TextMusicOutputStream extends AbstractMusicStream implements MusicOutputStream {

  private Appendable buffer;

  private final PropertyState rootProperty;

  private PropertyState currentProperty;

  private int line;

  private int column;

  /**
   * The constructor.
   *
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the output text.
   * @param property the {@link PropertyState}.
   */
  public TextMusicOutputStream(Appendable buffer, PropertyState property) {

    super();
    Objects.requireNonNull(buffer);
    Objects.requireNonNull(property);
    this.buffer = buffer;
    this.rootProperty = property;
    this.currentProperty = property;
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
  public void write(Object value) {

    if (value == null) {
      return;
    }
    try {
      String string = value.toString();
      int lastNewline = string.lastIndexOf(NEWLINE_CHAR);
      if (lastNewline >= 0) {
        this.line++;
        int newline = string.indexOf(NEWLINE_CHAR);
        while (newline < lastNewline) {
          this.line++;
          newline = string.indexOf(NEWLINE_CHAR, newline + 1);
        }
        this.column = (string.length() - lastNewline);
      } else {
        this.column += string.length();
      }
      this.buffer.append(string);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void write(char c) {

    try {
      if (c == '\n') {
        this.line++;
        this.column = 1;
      } else {
        this.column++;
      }
      this.buffer.append(c);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void writeProperty(String propertyName, String propertyValue) {

    writePropertyStart(propertyName);
    int suffixIndex = propertyValue.indexOf(this.currentProperty.suffix);
    if (suffixIndex >= 0) {
      write("\"");
      propertyValue.replace("\"", "\\\"");
    }
    write(propertyValue);
    if (suffixIndex >= 0) {
      write("\"");
    }
    writePropertyEnd(propertyName);
  }

  @Override
  public void writePropertyStart(String propertyName) {

    if (this.currentProperty.currentName != null) {
      if (this.currentProperty.child == null) {
        throw new IllegalArgumentException("Property deepth limit reached (" + this.rootProperty + ")");
      }
      this.currentProperty = this.currentProperty.child;
    }
    this.currentProperty.currentName = propertyName;
    write(propertyName);
    write(this.currentProperty.infix);
  }

  @Override
  public void writePropertyEnd(String propertyName) {

    if ((this.currentProperty.currentName == null) || (!this.currentProperty.currentName.equals(propertyName))) {
      throw new IllegalStateException(
          "Property (" + propertyName + ") cannot be ended - current property path is '" + this.rootProperty + "'.");
    }
    write(this.currentProperty.suffix);
    this.currentProperty.currentName = null;
    if (this.currentProperty.parent != null) {
      this.currentProperty = this.currentProperty.parent;
    }
  }

  @Override
  public void close() {

    if (this.buffer instanceof Closeable) {
      try {
        ((Closeable) this.buffer).close();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    this.buffer = null;
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @param property the {@link PropertyState}.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(Appendable out, PropertyState property) {

    return new TextMusicOutputStream(out, property);
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(OutputStream out) {

    return of(out, PropertyState.of());
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @param property the {@link PropertyState}.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(OutputStream out, PropertyState property) {

    try {
      OutputStreamWriter osw = new OutputStreamWriter(out, ENCODING);
      BufferedWriter writer = new BufferedWriter(osw);
      return new TextMusicOutputStream(writer, property);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }

}
