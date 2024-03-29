package io.github.musicdoc.song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.IntegerMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.StringMapper;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.property.Property;
import io.github.musicdoc.score.Score;

/**
 * {@link AbstractMapper Mapper} for {@link Song}.
 */
public abstract class SongMapper extends AbstractMapper<Song> {

  static final Song TEMPLATE = new Song();

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
    this.scoreMapped = getPropertyKey(TEMPLATE.score.getName()) != null;

    add(TEMPLATE.referenceNumber, IntegerMapper.INSTANCE);
    add(TEMPLATE.title, StringMapper.INSTANCE);
    add(TEMPLATE.artist, StringMapper.INSTANCE);
    add(TEMPLATE.album, StringMapper.INSTANCE);
    add(TEMPLATE.origin, StringMapper.INSTANCE);
    add(TEMPLATE.key, getKeyMapper());
    add(TEMPLATE.metre, getBeatMapper());
    add(TEMPLATE.tempo, getTempoMapper());
    add(TEMPLATE.unitNoteLength, getPlainFractionMapper());
    add(TEMPLATE.score, getScoreMapper());
    add(TEMPLATE.capo, IntegerMapper.INSTANCE);
    add(TEMPLATE.tags, StringMapper.INSTANCE);

  }

  private <T> void add(Property<T> property, AbstractMapper<T> valueMapper) {

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
   * @param propertyName the {@link Property#getName() property name} and {@link SongPropertyMapper#getName() name of
   *        the song property mapper}.
   * @return the format specific {@link SongPropertyMapper#getKey() property key} or {@code null} if the
   *         {@link Property} is not supported by this format.
   */
  protected abstract String getPropertyKey(String propertyName);

  @Override
  public Song read(MusicInputStream in, SongFormatContext context) {

    Song song = new Song();
    context.setSong(song);
    while (readProperty(in, context)) {
      // nothing else to do
    }
    if (!this.scoreMapped) {
      Score score = getScoreMapper().read(in, context);
      song.score.setValue(score);
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
      Property<?> property = song.getProperty(mapper.getName());
      String key = mapper.getKey();
      if (!key.isEmpty()) {
        if (property == song.score) {
          writeScore = false;
        }
        Object value = property.getValue();
        if ((value != null) && (!"".equals(value))) {
          out.writePropertyStart(key);
          mapper.write(song, out, context);
          out.writePropertyEnd(key);
        }
      }
    }
    if (writeScore) {
      getScoreMapper().write(song.score.getValue(), out, context);
    }
  }

}
