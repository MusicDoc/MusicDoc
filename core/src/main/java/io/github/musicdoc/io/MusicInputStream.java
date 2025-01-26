package io.github.musicdoc.io;

import io.github.mmm.scanner.CharStreamScanner;

/**
 * A {@link MusicStream} to read data from. Mainly designed as a stream of characters with methods to consume and of the
 * underlying text. Data may come from any source like a {@link String} or {@link java.io.Reader}.
 */
public interface MusicInputStream extends MusicStream {

  /**
   * @return the underlying {@link CharStreamScanner}.
   */
  CharStreamScanner getScanner();

  /**
   * @param maxLen the maximum number of characters to consume. Has to be in the range from 1 to 11
   *        ({@code -2147483648}).
   * @param acceptSign {@code true} to accept a leading signum ('+' or '-'), {@code false} otherwise (will prevent
   *        negative numbers).
   * @return an {@link Integer} from all {@link CharStreamScanner#next() next} consumed characters that belong to an
   *         {@link Integer} number or {@code null} if no integer was found and this stream remains unchanged.
   */
  Integer readInteger(int maxLen, boolean acceptSign);

  /**
   * @param expected the character to expect as {@link CharStreamScanner#next() next} in this stream.
   * @param warning {@code true} if a warning is collected in case the expected character was not present.
   * @return {@code true} if the expected character was found and consumer, {@code false} otherwise (and this stream
   *         remains unchanged).
   */
  default boolean expect(char expected, boolean warning) {

    CharStreamScanner scanner = getScanner();
    boolean found = scanner.expectOne(expected);
    if (!found) {
      addWarning("Expected " + expected);
    }
    return found;
  }

  /**
   * Lookahead for {@link #readPropertyStart()}. Will not consume data and therefore not change the state of this
   * stream.
   *
   * @param property the expected property to look for.
   * @return {@code true} if {@link #readPropertyStart()} will return the given {@code property}, {@code false}
   *         otherwise.
   */
  boolean isPropertyStart(String property);

  /**
   * @return the name of the property that has just started. If a property name was returned, this stream is filled with
   *         the property value that can be consumed via the provided methods (read*, expect*, peek*, skip*). May be
   *         {@code null} if the previous property has not been properly consumed.
   */
  String readPropertyStart();

  /**
   * @return the (rest) of the property value {@link CharStreamScanner#next() consumed} from this stream till the end of
   *         the {@link #readPropertyStart() current property}.
   */
  String readPropertyValue();

}
