package io.github.musicdoc.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * {@link MusicOutputStream} for simple text based formats such as MusicDoc or ABC.
 */
public class TextMusicOutputStream extends AbstractMusicStream implements MusicOutputStream, AttributePropertySuffix {

  private Appendable buffer;

  private final String propertyInfix;

  private String propertySuffix;

  private int line;

  private int column;

  /**
   * The constructor.
   *
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the output text.
   * @param propertyInfix the {@link #getPropertyInfix() property infix}.
   */
  public TextMusicOutputStream(Appendable buffer, String propertyInfix) {

    super();
    this.buffer = buffer;
    if ((propertyInfix == null) || (propertyInfix.isEmpty())) {
      this.propertyInfix = PROPERTIES_KEY_VALUE_SEPARATOR;
    } else {
      this.propertyInfix = propertyInfix;
    }
    this.line = 1;
    this.column = 1;
    this.propertySuffix = NEWLINE;
  }

  /**
   * @return the infix to append after {@link #startProperty(String) property name} and property value.
   */
  public String getPropertyInfix() {

    return this.propertyInfix;
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
  public void append(Object value) {

    if (value == null) {
      return;
    }
    try {
      this.buffer.append(value.toString());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void startProperty(String propertyName) {

    append(propertyName);
    append(this.propertyInfix);
  }

  @Override
  public void endProperty(String propertyName) {

    append(this.propertySuffix);
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
  public void close() throws Exception {

    this.buffer = null;
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(Appendable out) {

    return new TextMusicOutputStream(out, null);
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(OutputStream out) {

    return of(out, null);
  }

  /**
   * @param out the {@link OutputStream} to write to.
   * @param propertyInfix the {@link #getPropertyInfix() property infix}.
   * @return a new {@link TextMusicOutputStream} writing to the given {@link OutputStream}.
   */
  public static TextMusicOutputStream of(OutputStream out, String propertyInfix) {

    try {
      OutputStreamWriter osw = new OutputStreamWriter(out, ENCODING);
      BufferedWriter writer = new BufferedWriter(osw);
      return new TextMusicOutputStream(writer, propertyInfix);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }

}
