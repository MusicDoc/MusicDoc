package io.github.musicdoc.format;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.music.partiture.Partiture;
import io.github.musicdoc.music.partiture.PartitureMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * Abstract base class for a format to {@link #parse(String) parse} {@link Partiture}s.
 */
public abstract class SongFormat implements MusicParser<Partiture> {

  private static final Map<String, SongFormat> FORMAT_MAP = new HashMap<>();

  /**
   * The constructor.
   */
  protected SongFormat() {

    super();
    FORMAT_MAP.put(getName(), this);
  }

  /**
   * @return the underlying {@link PartitureMapper} to delegate to.
   */
  protected abstract PartitureMapper getPartitureMapper();

  /**
   * @return the name of this formatSection.
   */
  public abstract String getName();

  /**
   * @param lyrics the lyrics to parse.
   * @return the {@link Partiture} parsed from the given {@code lyrics}.
   */
  @Override
  public Partiture parse(String lyrics) {

    return getPartitureMapper().parse(lyrics);
  }

  @Override
  public Partiture parse(CharStream chars) {

    return getPartitureMapper().parse(chars);
  }

  /**
   * @param partiture the {@link Partiture} to format.
   * @return the given {@link Partiture} formatted as text (lyrics) in this {@link SongFormat}.
   */
  public final String format(Partiture partiture) {

    return format(partiture, new MusicFormatOptions());
  }

  /**
   * @param partiture the {@link Partiture} to format.
   * @param options the {@link MusicFormatOptions}.
   * @return the given {@link Partiture} formatted as text (lyrics) in this {@link SongFormat}.
   */
  public String format(Partiture partiture, MusicFormatOptions options) {

    StringBuilder buffer = new StringBuilder();
    try {
      getPartitureMapper().format(partiture, buffer, options);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return buffer.toString();
  }

  /**
   * @param name the {@link #getName() name} of the requested {@link SongFormat}.
   * @return the {@link SongFormat} with the given {@link #getName() name} or {@code null} if not exists.
   */
  public static final SongFormat get(String name) {

    return FORMAT_MAP.get(name);
  }

  @Override
  public String toString() {

    return getName();
  }
}
