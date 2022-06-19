package io.github.musicdoc.music.stave;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.AppendableAdapter;
import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.format.MusicFormatter;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.clef.ClefMapper;
import io.github.musicdoc.music.harmony.MusicalKeyMapper;
import io.github.musicdoc.music.rythm.beat.BeatMapper;
import io.github.musicdoc.parser.CharStream;

public class StaveMapper extends AbstractMapper<Stave> {

  public static final StaveMapper INSTANCE = new StaveMapper();

  public static final StaveMapper OPEN_SONG = INSTANCE;

  public static final StaveMapper MUSIC_DOC = INSTANCE;

  public static final StaveMapper ABC = new StaveMapper(new StaveProperties('C', 'K', 'Q', 'V'));

  private static final String PROPERTIES_SEPARATOR_STRING = "" + FormatConstants.PROPERTIES_SEPARATOR;

  private final StaveProperties properties;

  /**
   * The constructor.
   */
  public StaveMapper() {

    this.properties = new StaveProperties();
  }

  private StaveMapper(StaveProperties keys) {

    this.properties = keys;
  }

  @Override
  public Stave parse(CharStream chars) {

    Stave stave = new Stave();
    StaveBracket bracket = StaveBracketMapper.INSTANCE.parse(chars);
    stave.setBracket(bracket);
    boolean found = true;
    while (found) {
      chars.skipWhile(' ');
      found = parseProperty(chars, stave);
      if (found) {
        chars.skipWhile(' ');
        char c = chars.peek();
        if ((c == FormatConstants.PROPERTIES_SEPARATOR) || (c == ',')) {
          found = true;
          chars.next();
        }
      }
    }
    return stave;
  }

  private boolean parseProperty(CharStream chars, Stave stave) {

    char propertyKey = chars.peek();
    StavePropertyMapper<?> propertyMapper = this.properties.map.get(propertyKey);
    if (propertyMapper == null) {
      return false;
    }
    String prefix = "" + propertyKey + FormatConstants.PROPERTIES_KEY_VALUE_SEPARATOR;
    if (!chars.expect(prefix, false)) {
      return false;
    }
    chars.skipWhile(' ');
    propertyMapper.parse(chars, stave);
    return true;
  }

  @Override
  public void format(Stave stave, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (stave == null) {
      return;
    }
    AppendableAdapter adapter = new AppendableAdapter(buffer);
    for (StavePropertyMapper<?> mapper : this.properties.map.values()) {
      adapter.setSeparatorIfUpdated(PROPERTIES_SEPARATOR_STRING);
      mapper.format(stave, adapter, options);
    }
  }

  private static class StavePropertyMapper<T> implements MusicFormatter<Stave> {

    private final StaveProperty<T> property;

    private final AbstractMapper<T> mapper;

    private final char key;

    public StavePropertyMapper(StaveProperty<T> property, AbstractMapper<T> mapper, char key) {

      this.property = property;
      this.mapper = mapper;
      this.key = key;
    }

    public boolean parse(CharStream chars, Stave stave) {

      T value = this.mapper.parse(chars);
      if (value == null) {
        // invalid property value, log error
        return false;
      }
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
      return true;
    }

    @Override
    public void format(Stave stave, Appendable buffer, MusicFormatOptions options) throws IOException {

      T value = this.property.get(stave);
      if (value == null) {
        return;
      }
      buffer.append(this.key);
      buffer.append(PROPERTIES_KEY_VALUE_SEPARATOR);
      this.mapper.format(value, buffer, options);
    }
  }

  private static class StaveProperties {

    private final Map<Character, StavePropertyMapper<?>> map;

    public StaveProperties() {

      this(PROPERTY_CLEV, PROPERTY_KEY, PROPERTY_BEAT, PROPERTY_VOICE);
    }

    public StaveProperties(char clef, char key, char beat, char voice) {

      this(new StavePropertyMapper<>(StavePropertyClef.INSTANCE, ClefMapper.INSTANCE, clef),
          new StavePropertyMapper<>(StavePropertyKey.INSTANCE, MusicalKeyMapper.INSTANCE, key),
          new StavePropertyMapper<>(StavePropertyBeat.INSTANCE, BeatMapper.INSTANCE, beat),
          new StavePropertyMapper<>(StavePropertyVoice.INSTANCE, StaveVoiceMapper.INSTANCE, voice));
    }

    public StaveProperties(StavePropertyMapper... keys) {

      this(new HashMap<Character, StavePropertyMapper<?>>(keys.length));
      for (StavePropertyMapper<?> key : keys) {
        this.map.put(key.key, key);
      }
    }

    public StaveProperties(Map<Character, StavePropertyMapper<?>> map) {

      this.map = map;
    }
  }

}
