package io.github.musicdoc.music.format;

/**
 * Interface for a mapper that can both read and write musical objects of a specific type ({@code <T>}).
 *
 * @param <T> type of the musical object to map.
 * @see AbstractMapper
 */
public interface Mapper<T> extends MusicReader<T>, MusicWriter<T> {

}
