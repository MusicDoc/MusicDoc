package io.github.musicdoc.io;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.format.SongFormatMessage;
import io.github.musicdoc.music.format.SongFormatMessageType;

/**
 * Abstract base implementation of {@link MusicStream}.
 */
public abstract class AbstractMusicStream implements MusicStream, FormatConstants {

  private List<SongFormatMessage> messages;

  /**
   * The constructor.
   */
  public AbstractMusicStream() {

    super();
  }

  @Override
  public void addMessage(SongFormatMessageType type, String text) {

    getMessages().add(new SongFormatMessage(getLine(), getColumn(), type, text));
  }

  /**
   * @return the {@link List} of {@link SongFormatMessage}s.
   */
  public List<SongFormatMessage> getMessages() {

    if (this.messages == null) {
      this.messages = new ArrayList<>();
    }
    return this.messages;
  }

}
