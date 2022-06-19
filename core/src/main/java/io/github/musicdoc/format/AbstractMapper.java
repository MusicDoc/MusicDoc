package io.github.musicdoc.format;

/**
 * {@link AbstractMusicParser} that is also a {@link MusicFormatter}.
 *
 * @param <T> type of the musical object to map.
 */
public abstract class AbstractMapper<T> extends AbstractMusicParser<T> implements MusicFormatter<T>, FormatConstants {

}
