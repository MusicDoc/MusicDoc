package io.github.musicdoc.parser;

import java.util.Objects;

/**
 * A warning message during parsing.
 */
public class MusicParserMessage implements TextPosition {

  private final int line;

  private final int column;

  private final MusicParserMessageType type;

  private final String text;

  /**
   * The constructor.
   *
   * @param line the {@link #getLine() line}.
   * @param column the {@link #getColumn() column}.
   * @param type the {@link #getType() type}.
   * @param text the {@link #getText() text}.
   */
  public MusicParserMessage(int line, int column, MusicParserMessageType type, String text) {

    super();
    Objects.requireNonNull(type);
    Objects.requireNonNull(text);
    this.line = line;
    this.column = column;
    this.type = type;
    this.text = text;
  }

  @Override
  public int getLine() {

    return this.line;
  }

  @Override
  public int getColumn() {

    return this.column;
  }

  /**
   * @return the {@link MusicParserMessageType}.
   */
  public MusicParserMessageType getType() {

    return this.type;
  }

  /**
   * @return the message text.
   */
  public String getText() {

    return this.text;
  }
}