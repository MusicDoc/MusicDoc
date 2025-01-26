package io.github.musicdoc.song;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.SimpleProperty;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for a {@link WritableProperty property} of a {@link Song}.
 */
public class SongPropertyMapper extends AbstractMapper<Song> {

  private final String propertyName;

  private final String key;

  @SuppressWarnings("rawtypes")
  private final AbstractMapper valueMapper;

  /**
   * The constructor.
   *
   * @param propertyName the {@link WritableProperty#getName() name} of the {@link WritableProperty} to map.
   * @param key the {@link #getKey() song format key}.
   * @param valueMapper the optional {@link AbstractMapper mapper} to map the the {@link WritableProperty#get() property
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
   * @return the {@link WritableProperty#getName() property name}.
   */
  public String getName() {

    return this.propertyName;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void write(Song song, MusicOutputStream out, SongFormatContext context) {

    WritableProperty<?> property = null;
    if (song != null) {
      property = song.getProperty(this.propertyName); // , true);
    }
    if (property == null) {
      // log warning
      return;
    }
    Object value = property.get();
    if (value == null) {
      return;
    }
    if (this.valueMapper == null) {
      out.write(value.toString());
    } else {
      this.valueMapper.write(value, out, context);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public Song read(MusicInputStream in, SongFormatContext context) {

    Song song = context.getSong();
    if (song != null) {
      WritableProperty<?> property = song.getRequiredProperty(this.propertyName);
      if (this.valueMapper == null) {
        if (property instanceof SimpleProperty) {
          String value = in.getScanner().readUntil('\n', true).trim();
          ((SimpleProperty<?>) property).setAsString(value);
        } else {
          throw new IllegalStateException(this.propertyName);
        }
      } else {
        Object value = this.valueMapper.read(in, context);
        ((WritableProperty) property).set(value);
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
   * @param <T> type of the {@link WritableProperty#get() property value}.
   * @param property the {@link WritableProperty} to map.
   * @param key the {@link #getKey() key}.
   * @param valueMapper the {@link AbstractMapper}.
   * @return the {@link SongPropertyMapper}.
   */
  public static <T> SongPropertyMapper of(WritableProperty<T> property, char key, AbstractMapper<T> valueMapper) {

    return of(property, Character.toString(key), valueMapper);
  }

  /**
   * @param <T> type of the {@link WritableProperty#get() property value}.
   * @param property the {@link WritableProperty} to map.
   * @param key the {@link #getKey() key}.
   * @param valueMapper the {@link AbstractMapper}.
   * @return the {@link SongPropertyMapper}.
   */
  public static <T> SongPropertyMapper of(WritableProperty<T> property, String key, AbstractMapper<T> valueMapper) {

    return new SongPropertyMapper(property.getName(), key, valueMapper);
  }

}
