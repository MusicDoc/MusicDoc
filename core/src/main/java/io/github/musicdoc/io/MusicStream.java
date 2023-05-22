package io.github.musicdoc.io;

import io.github.mmm.base.text.TextFormatProcessor;

/**
 * A stream of characters with method to consume and of the underlying text. Data may come from any source like a
 * {@link String} or {@link java.io.Reader}.
 */
public interface MusicStream extends TextFormatProcessor, AutoCloseable {

  @Override
  void close();

}
