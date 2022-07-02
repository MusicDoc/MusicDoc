package io.github.musicdoc.io;

import io.github.musicdoc.music.format.SongFormatMessage;
import io.github.musicdoc.music.format.SongFormatMessageType;
import io.github.musicdoc.music.format.TextPosition;

/**
 * A stream of characters with method to consume and of the underlying text. Data may come from any source like a
 * {@link String} or {@link java.io.Reader}.
 */
public interface MusicStream extends TextPosition, AutoCloseable {

  /**
   * Adds a {@link SongFormatMessageType#HINT hint} {@link SongFormatMessage} for the current {@link TextPosition}.
   *
   * @param text the {@link SongFormatMessage#getText() text of the message}.
   */
  default void addHint(String text) {

    addMessage(SongFormatMessageType.HINT, text);
  }

  /**
   * Adds a {@link SongFormatMessageType#WARNING warning} {@link SongFormatMessage} for the current
   * {@link TextPosition}.
   *
   * @param text the {@link SongFormatMessage#getText() text of the message}.
   */
  default void addWarning(String text) {

    addMessage(SongFormatMessageType.WARNING, text);
  }

  /**
   * Adds a {@link SongFormatMessageType#ERROR error} {@link SongFormatMessage} for the current {@link TextPosition}.
   *
   * @param text the {@link SongFormatMessage#getText() text of the message}.
   */
  default void addError(String text) {

    addMessage(SongFormatMessageType.ERROR, text);
  }

  /**
   * Adds a {@link SongFormatMessage} for the current {@link TextPosition}.
   *
   * @param type the {@link SongFormatMessage#getType() type of the message}.
   * @param text the {@link SongFormatMessage#getText() text of the message}.
   */
  void addMessage(SongFormatMessageType type, String text);

}
