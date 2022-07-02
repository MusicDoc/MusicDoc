package io.github.musicdoc.music.song;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.property.Property;

/**
 * {@link AbstractMapper Mapper} for a {@link Property} of a {@link Song}.
 */
public class SongPropertyMapper extends AbstractMapper<Song> {

  private final String propertyName;

  private final String key;

  @SuppressWarnings("rawtypes")
  private final AbstractMapper valueMapper;

  /**
   * The constructor.
   *
   * @param propertyName the {@link Property#getName() name} of the {@link Property} to map.
   * @param key the {@link #getKey() song format key}.
   * @param valueMapper the optional {@link AbstractMapper mapper} to map the the {@link Property#getValue() property
   *        value}.
   */
  @SuppressWarnings("rawtypes")
  public SongPropertyMapper(String propertyName, String key, AbstractMapper valueMapper) {

    super();
    this.propertyName = propertyName;
    this.key = key;
    this.valueMapper = valueMapper;
  }

  /**
   * @return key the song-format key of the mapped property.
   */
  public String getKey() {

    return this.key;
  }

  /**
   * @return the {@link Property#getName() property name}.
   */
  public String getName() {

    return this.propertyName;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void format(Song song, MusicOutputStream out, SongFormatOptions options) {

    Property<?> property = null;
    if (song != null) {
      property = song.getProperty(this.propertyName, true);
    }
    if (property == null) {
      // log warning
      return;
    }
    Object value = property.getValue();
    if (value == null) {
      return;
    }
    if (this.valueMapper == null) {
      out.append(value.toString());
    } else {
      this.valueMapper.format(value, out, options);
    }
  }

  @Override
  public Song parse(MusicInputStream chars, SongFormatOptions options) {

    Song song = options.getSong();
    if (song != null) {
      if (this.valueMapper == null) {
        String value = chars.readUntil('\n', true).trim();
        song.setValueAsString(this.propertyName, value);
      } else {
        Object value = this.valueMapper.parse(chars, options);
        song.setValue(this.propertyName, value);
      }
    }
    return song;
  }

  @Override
  protected SongFormat getFormat() {

    return getFormat(this.valueMapper);
  }

  /**
   * @param <T> type of the {@link Property#getValue() property value}.
   * @param property the {@link Property} to map.
   * @param key the {@link #getKey() key}.
   * @param valueMapper the {@link AbstractMapper}.
   * @return the {@link SongPropertyMapper}.
   */
  public static <T> SongPropertyMapper of(Property<T> property, char key, AbstractMapper<T> valueMapper) {

    return of(property, Character.toString(key), valueMapper);
  }

  /**
   * @param <T> type of the {@link Property#getValue() property value}.
   * @param property the {@link Property} to map.
   * @param key the {@link #getKey() key}.
   * @param valueMapper the {@link AbstractMapper}.
   * @return the {@link SongPropertyMapper}.
   */
  public static <T> SongPropertyMapper of(Property<T> property, String key, AbstractMapper<T> valueMapper) {

    return new SongPropertyMapper(property.getName(), key, valueMapper);
  }

}
