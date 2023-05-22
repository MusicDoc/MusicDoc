package io.github.musicdoc.io;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.base.text.TextFormatMessage;
import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.format.SongFormatMessage;

/**
 * Abstract base implementation of {@link MusicStream}.
 */
public abstract class AbstractMusicStream implements MusicStream, FormatConstants {

  private List<TextFormatMessage> messages;

  /**
   * The constructor.
   */
  public AbstractMusicStream() {

    super();
  }

  /**
   * @return the {@link List} of {@link SongFormatMessage}s.
   */
  @Override
  public List<TextFormatMessage> getMessages() {

    if (this.messages == null) {
      this.messages = new ArrayList<>();
    }
    return this.messages;
  }

}
