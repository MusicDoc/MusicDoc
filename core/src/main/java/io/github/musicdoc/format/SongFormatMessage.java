package io.github.musicdoc.format;

import java.util.Objects;

/**
 * A warning message that occurred whilst reading (parsing) or writing (formatting) musical objects.
 */
public class SongFormatMessage implements TextPosition {

  private final int line;

  private final int column;

  private final SongFormatMessageType type;

  private final String text;

  /**
   * The constructor.
   *
   * @param line the {@link #getLine() line}.
   * @param column the {@link #getColumn() column}.
   * @param type the {@link #getType() type}.
   * @param text the {@link #getText() text}.
   */
  public SongFormatMessage(int line, int column, SongFormatMessageType type, String text) {

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
   * @return the {@link SongFormatMessageType}.
   */
  public SongFormatMessageType getType() {

    return this.type;
  }

  /**
   * @return the message text.
   */
  public String getText() {

    return this.text;
  }
}