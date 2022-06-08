package io.github.musicdoc.format;

import io.github.musicdoc.parser.AbstractSubParser;

/**
 * {@link AbstractSubParser} that is also a {@link Formatter}.
 *
 * @param <T> type of the musical object to map.
 */
public abstract class AbstractMapper<T> extends AbstractSubParser<T> implements Formatter<T>, FormatConstants {

}
