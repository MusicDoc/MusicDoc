package io.github.musicdoc.music.stave;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.io.AttributePropertySuffix;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.io.TextMusicInputStream;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.MusicalKeyMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapper;

/**
 * {@link AbstractMapper Mapper} for {@link Stave}.
 */
public abstract class StaveMapper extends AbstractMapper<Stave> {

  // public static final StaveMapper ABC = new StaveMapper(new StaveProperties('C', 'K', 'Q', 'V'));

  private static final String PROPERTIES_SEPARATOR_STRING = "" + FormatConstants.PROPERTIES_SEPARATOR;

  private final StaveProperties properties;

  /**
   * The constructor.
   *
   * @param properties the {@link StaveProperties}
   */
  protected StaveMapper(StaveProperties properties) {

    this.properties = properties;
  }

  @Override
  public Stave parse(MusicInputStream chars, SongFormatOptions options) {

    Stave stave = new Stave();
    if (chars instanceof TextMusicInputStream) {
      // TODO format specific
      ((TextMusicInputStream) chars).setPropertySuffix(FormatConstants.PROPERTIES_SEPARATOR);
    }
    options.setStave(stave);
    StaveBracket bracket = getBracketMapper().parse(chars, options);
    stave.setBracket(bracket);
    boolean found = true;
    while (found) {
      chars.skipWhile(' ');
      found = parseProperty(chars, options);
      if (!found) {
        // TODO
      }
    }
    return stave;
  }

  /**
   * @return the {@link StaveBracketMapper}.
   */
  protected abstract StaveBracketMapper getBracketMapper();

  private boolean parseProperty(MusicInputStream chars, SongFormatOptions options) {

    String property = chars.readPropertyStart();
    if (property == null) {
      return false;
    }
    StavePropertyMapper<?> propertyMapper = this.properties.map.get(property);
    if (propertyMapper == null) {
      chars.addError("Unknownm property: " + property);
      chars.readPropertyValue();
      return true;
    }
    chars.skipWhile(' ');
    Stave stave = propertyMapper.parse(chars, options);
    String rest = chars.readPropertyValue();
    assert (rest.isEmpty()) : rest;
    return (stave != null);
  }

  @Override
  public void format(Stave stave, MusicOutputStream out, SongFormatOptions options) {

    if (stave == null) {
      return;
    }
    AttributePropertySuffix attribute = null;
    String suffix = null;
    if (out instanceof AttributePropertySuffix) {
      attribute = (AttributePropertySuffix) out;
      suffix = attribute.getPropertySuffix();
      attribute.setPropertySuffix(PROPERTIES_SEPARATOR_STRING);
    }
    for (StavePropertyMapper<?> mapper : this.properties.map.values()) {
      mapper.format(stave, out, options);
    }
    if (attribute != null) {
      attribute.setPropertySuffix(suffix);
    }
  }

  private static class StavePropertyMapper<T> extends AbstractMapper<Stave> {

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
    public Stave parse(MusicInputStream chars, SongFormatOptions options) {

      T value = this.mapper.parse(chars, options);
      if (value == null) {
        chars.addError("Invalid stave property value for key " + this.key + ".");
        return null;
      }
      Stave stave = options.getStave();
      if (this.property != StavePropertyVoice.INSTANCE) {
        T old = this.property.get(stave);
        if (old != null) {
          // duplicate property, log warning
          if (!old.equals(value)) {
            // duplicate property with new value, log error
          }
        }
      }
      this.property.set(stave, value);
      return stave;
    }

    @Override
    public void format(Stave stave, MusicOutputStream out, SongFormatOptions options) {

      T value = this.property.get(stave);
      if (value == null) {
        return;
      }
      out.append(this.key);
      out.append(PROPERTIES_KEY_VALUE_SEPARATOR);
      this.mapper.format(value, out, options);
    }
  }

  protected static class StaveProperties {

    private final Map<String, StavePropertyMapper<?>> map;

    public StaveProperties(ClefMapper clefMapper, MusicalKeyMapper keyMapper, BeatMapper beatMapper,
        StaveVoiceMapper voiceMapper) {

      this(new StavePropertyMapper<>(StavePropertyClef.INSTANCE, clefMapper, PROPERTY_CLEF),
          new StavePropertyMapper<>(StavePropertyKey.INSTANCE, keyMapper, PROPERTY_KEY),
          new StavePropertyMapper<>(StavePropertyBeat.INSTANCE, beatMapper, PROPERTY_METER),
          new StavePropertyMapper<>(StavePropertyVoice.INSTANCE, voiceMapper, PROPERTY_VOICE));
    }

    public StaveProperties(StavePropertyMapper... keys) {

      this(new HashMap<String, StavePropertyMapper<?>>(keys.length));
      for (StavePropertyMapper<?> key : keys) {
        this.map.put(key.key, key);
      }
    }

    public StaveProperties(Map<String, StavePropertyMapper<?>> map) {

      this.map = map;
    }
  }

}
