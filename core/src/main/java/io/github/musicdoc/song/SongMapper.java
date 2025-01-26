package io.github.musicdoc.song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.mmm.property.WritableProperty;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.AlbumTitleMapper;
import io.github.musicdoc.format.IntegerMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.StringMapper;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.score.Score;

/**
 * {@link AbstractMapper Mapper} for {@link Song}.
 */
public abstract class SongMapper extends AbstractMapper<Song> {

  static final Song TEMPLATE = Song.of();

  private final Map<String, SongPropertyMapper> propertyKeyMap;

  private final List<SongPropertyMapper> properties;

  private final boolean scoreMapped;

  /**
   * The constructor.
   */
  public SongMapper() {

    super();

    int capacity = TEMPLATE.getProperties().size();
    this.propertyKeyMap = new HashMap<>(capacity);
    this.properties = new ArrayList<>();
    this.scoreMapped = getPropertyKey(TEMPLATE.Score().getName()) != null;

    add(TEMPLATE.ReferenceNumber(), IntegerMapper.INSTANCE);
    add(TEMPLATE.Title(), StringMapper.INSTANCE);
    // add(TEMPLATE.Artist(), StringMapper.INSTANCE);
    add(TEMPLATE.Album(), AlbumTitleMapper.INSTANCE);
    add(TEMPLATE.Origin(), StringMapper.INSTANCE);
    add(TEMPLATE.Key(), getKeyMapper());
    add(TEMPLATE.Metre(), getBeatMapper());
    add(TEMPLATE.Tempo(), getTempoMapper());
    add(TEMPLATE.UnitNoteLength(), getPlainFractionMapper());
    add(TEMPLATE.Score(), getScoreMapper());
    add(TEMPLATE.Capo(), IntegerMapper.INSTANCE);
    add(TEMPLATE.Tags(), StringMapper.INSTANCE);

  }

  private <T> void add(WritableProperty<T> property, AbstractMapper<T> valueMapper) {

    if (valueMapper == null) {
      return; // property not supported
    }
    String propertyName = property.getName();
    String propertyKey = getPropertyKey(propertyName);
    if (propertyKey == null) {
      return; // property not supported
    }
    SongPropertyMapper mapper = new SongPropertyMapper(propertyName, propertyKey, valueMapper);
    SongPropertyMapper duplicate = this.propertyKeyMap.put(toLowerCase(propertyKey), mapper);
    if (duplicate != null) {
      throw new IllegalStateException("Duplicate property key: " + propertyKey);
    }
    this.properties.add(mapper);
  }

  /**
   * @param propertyName the {@link WritableProperty#getName() property name} and {@link SongPropertyMapper#getName()
   *        name of the song property mapper}.
   * @return the format specific {@link SongPropertyMapper#getKey() property key} or {@code null} if the
   *         {@link WritableProperty} is not supported by this format.
   */
  protected abstract String getPropertyKey(String propertyName);

  @Override
  public Song read(MusicInputStream in, SongFormatContext context) {

    Song song = Song.of();
    context.setSong(song);
    while (readProperty(in, context)) {
      // nothing else to do
    }
    if (!this.scoreMapped) {
      Score score = getScoreMapper().read(in, context);
      song.Score().set(score);
    }
    return song;
  }

  private boolean readProperty(MusicInputStream in, SongFormatContext context) {

    String property = in.readPropertyStart();
    if (property != null) {
      SongPropertyMapper mapper = this.propertyKeyMap.get(toLowerCase(property));
      if (mapper == null) {
        String garbage = in.readPropertyValue();
        in.addError("Unknown property '" + property + "' (skipping value: " + garbage + ").");
      } else {
        mapper.read(in, context);
        String rest = in.readPropertyValue();
        if (!rest.isBlank()) {
          in.addWarning("Ignored garbage at the end of previous line.");
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public void write(Song song, MusicOutputStream out, SongFormatContext context) {

    context.setSong(song);
    boolean writeScore = true;
    for (SongPropertyMapper mapper : this.properties) {
      WritableProperty<?> property = song.getProperty(mapper.getName());
      String key = mapper.getKey();
      if (!key.isEmpty()) {
        if (property == song.Score()) {
          writeScore = false;
        }
        Object value = property.get();
        if ((value != null) && (!"".equals(value))) {
          out.writePropertyStart(key);
          mapper.write(song, out, context);
          out.writePropertyEnd(key);
        }
      }
    }
    if (writeScore) {
      getScoreMapper().write(song.Score().get(), out, context);
    }
  }

}
