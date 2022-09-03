package io.github.musicdoc.stave;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.clef.ClefMapper;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.key.MusicalKeyMapper;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.metre.MetreMapper;
import io.github.musicdoc.stave.voice.StaveVoiceMapper;

/**
 * {@link AbstractMapper Mapper} for {@link Stave}.
 *
 * @param <T> type of the {@link AbstractStave} to map.
 */
public abstract class AbstractStaveMapperProperties<T extends AbstractStave<T>> extends AbstractStaveMapper<T> {

  private final StaveProperties properties;

  /**
   * The constructor.
   */
  protected AbstractStaveMapperProperties() {

    super();
    this.properties = createProperties();
  }

  /**
   * @return the {@link StaveProperties}.
   */
  protected abstract StaveProperties createProperties();

  @Override
  public T read(MusicInputStream in, SongFormatContext context) {

    T stave = null;
    boolean found = true;
    while (found) {
      in.skipWhile(' ');
      String property = in.readPropertyStart();
      if (property == null) {
        found = false;
      } else {
        if (stave == null) {
          stave = createStave(context.getClef(), context.getKey(), context.getMetre());
          context.setStave(stave);
        }
        found = parseProperty(property, in, context);
      }
      if (!found) {
        // TODO
      }
    }
    return stave;
  }

  @SuppressWarnings("unchecked")
  private boolean parseProperty(String property, MusicInputStream in, SongFormatContext context) {

    StavePropertyMapper<?> propertyMapper = this.properties.map.get(property);
    if (propertyMapper == null) {
      in.addError("Unknownm property: " + property);
      in.readPropertyValue();
      return true;
    }
    in.skipWhile(' ');
    T stave = (T) propertyMapper.read(in, context);
    String rest = in.readPropertyValue();
    assert (rest.isEmpty()) : rest;
    return (stave != null);
  }

  @Override
  public void write(T stave, MusicOutputStream out, SongFormatContext context) {

    if (stave == null) {
      return;
    }
    for (StavePropertyMapper<?> mapper : this.properties.map.values()) {
      mapper.write(stave, out, context);
    }
  }

  private static class StavePropertyMapper<T> extends AbstractMapper<AbstractStave<?>> {

    private final StaveProperty<T> property;

    private final AbstractMapper<T> mapper;

    private final String key;

    public StavePropertyMapper(StaveProperty<T> property, AbstractMapper<T> mapper, String key) {

      this.property = property;
      this.mapper = mapper;
      this.key = key;
    }

    @Override
    protected SongFormat getFormat() {

      throw new UnsupportedOperationException();
      // return null;
    }

    @Override
    public AbstractStave<?> read(MusicInputStream in, SongFormatContext context) {

      T value = this.mapper.read(in, context);
      if (value == null) {
        in.addError("Invalid stave property value for key " + this.key + ".");
        return null;
      }
      AbstractStave<?> stave = context.getStave();
      if (this.property != StavePropertyVoice.INSTANCE) {
        T old = this.property.get(stave);
        if (old != null) {
          in.addWarning("Duplicate property '" + this.key + "' - overriding existing value.");
        }
      }
      AbstractStave<?> newStave = this.property.set(stave, value);
      if (stave != newStave) {
        context.setStave(newStave);
      }
      return newStave;
    }

    @Override
    public void write(AbstractStave<?> stave, MusicOutputStream out, SongFormatContext context) {

      T value = this.property.get(stave);
      if (value == null) {
        return;
      }
      out.write(this.key);
      out.write(PROPERTIES_KEY_VALUE_SEPARATOR);
      this.mapper.write(value, out, context);
    }
  }

  protected static class StaveProperties {

    private final Map<String, StavePropertyMapper<?>> map;

    public StaveProperties(ClefMapper clefMapper, MusicalKeyMapper keyMapper, MetreMapper beatMapper) {

      this(new StavePropertyMapper<>(StavePropertyClef.INSTANCE, clefMapper, PROPERTY_CLEF),
          new StavePropertyMapper<>(StavePropertyKey.INSTANCE, keyMapper, PROPERTY_KEY),
          new StavePropertyMapper<>(StavePropertyBeat.INSTANCE, beatMapper, PROPERTY_METRE));
    }

    public StaveProperties(ClefMapper clefMapper, MusicalKeyMapper keyMapper, MetreMapper beatMapper, StaveVoiceMapper voiceMapper) {

      this(new StavePropertyMapper<>(StavePropertyClef.INSTANCE, clefMapper, PROPERTY_CLEF),
          new StavePropertyMapper<>(StavePropertyKey.INSTANCE, keyMapper, PROPERTY_KEY),
          new StavePropertyMapper<>(StavePropertyBeat.INSTANCE, beatMapper, PROPERTY_METRE),
          new StavePropertyMapper<>(StavePropertyVoice.INSTANCE, voiceMapper, PROPERTY_VOICE));
    }

    public StaveProperties(StavePropertyMapper... keys) {

      this(new HashMap<String, StavePropertyMapper<?>>(keys.length));
      for (StavePropertyMapper<?> key : keys) {
        this.map.put(key.key, key);
      }
    }

    public StaveProperties(Map<String, StavePropertyMapper<?>> map) {

      super();
      this.map = map;
    }
  }

}
