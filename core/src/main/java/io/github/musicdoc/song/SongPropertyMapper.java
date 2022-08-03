package io.github.musicdoc.song;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
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
  public void write(Song song, MusicOutputStream out, SongFormatContext context) {

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
      out.write(value.toString());
    } else {
      this.valueMapper.write(value, out, context);
    }
  }

  @Override
  public Song read(MusicInputStream in, SongFormatContext context) {

    Song song = context.getSong();
    if (song != null) {
      if (this.valueMapper == null) {
        String value = in.readUntil('\n', true).trim();
        song.setValueAsString(this.propertyName, value);
      } else {
        Object value = this.valueMapper.read(in, context);
        song.setValue(this.propertyName, value);
      }
    }
    return song;
  }

  @Override
  protected SongFormat getFormat() {

    return getFormat(this.valueMapper);
  }

  @Override
  public String toString() {

    return this.key + "=" + this.propertyName;
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
