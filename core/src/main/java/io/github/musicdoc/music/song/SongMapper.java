package io.github.musicdoc.music.song;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.IntegerMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.StringMapper;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.property.Property;

/**
 * {@link AbstractMapper Mapper} for {@link Song}.
 */
public abstract class SongMapper extends AbstractMapper<Song> {

  static final Song TEMPLATE = new Song();

  private final Map<String, SongPropertyMapper> propertyKeyMap;

  private final Map<String, SongPropertyMapper> propertyNameMap;

  /**
   * The constructor.
   */
  public SongMapper() {

    super();

    int capacity = TEMPLATE.getProperties().size();
    this.propertyKeyMap = new HashMap<>(capacity);
    this.propertyNameMap = new HashMap<>(capacity);

    add(TEMPLATE.referenceNumber, IntegerMapper.INSTANCE);
    add(TEMPLATE.title, StringMapper.INSTANCE);
    add(TEMPLATE.composer, StringMapper.INSTANCE);
    add(TEMPLATE.album, StringMapper.INSTANCE);
    add(TEMPLATE.origin, StringMapper.INSTANCE);
    add(TEMPLATE.key, getKeyMapper());
    add(TEMPLATE.beat, getBeatMapper());
    add(TEMPLATE.score, getScoreMapper());
    add(TEMPLATE.tempo, getTempoMapper());
    add(TEMPLATE.capo, IntegerMapper.INSTANCE);
    // add fraction mapper?
    add(TEMPLATE.unitNoteLength, getBeatMapper());

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
    duplicate = this.propertyNameMap.put(mapper.getName(), mapper);
    if (duplicate != null) {
      throw new IllegalStateException("Duplicate property name: " + propertyKey);
    }
  }

  /**
   * @param propertyName the {@link Property#getName() property name} and {@link SongPropertyMapper#getName() name of
   *        the song property mapper}.
   * @return the format specific {@link SongPropertyMapper#getKey() property key} or {@code null} if the
   *         {@link Property} is not supported by this format.
   */
  protected abstract String getPropertyKey(String propertyName);

  @Override
  public void write(Song song, MusicOutputStream out, SongFormatContext context) {

    boolean writeScore = true;
    for (Property<?> property : song.getProperties()) {
      String name = property.getName();
      SongPropertyMapper mapper = this.propertyNameMap.get(name);
      if (mapper == null) {
        // log warning
      } else {
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
    }
    if (writeScore) {
      getScoreMapper().write(song.score.getValue(), out, context);
    }
  }

  @Override
  public Song read(MusicInputStream in, SongFormatContext context) {

    Song song = new Song();
    context.setSong(song);
    // TODO support property continuation (e.g. "+: ...")
    while (readProperty(in, context)) {
      // nothing else to do
    }
    if (!this.propertyNameMap.containsKey(toLowerCase(TEMPLATE.score.getName()))) {
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
      }
      in.expect(NEWLINE, true);
      return true;
    }
    return false;
  }

}
