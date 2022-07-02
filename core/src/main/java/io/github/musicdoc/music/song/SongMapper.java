package io.github.musicdoc.music.song;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreMapper;
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
   *
   * @param propertyMappers the {@link SongPropertyMapper}s.
   */
  public SongMapper(SongPropertyMapper... propertyMappers) {

    super();
    this.propertyKeyMap = new HashMap<>(propertyMappers.length);
    this.propertyNameMap = new HashMap<>(propertyMappers.length);
    for (SongPropertyMapper mapper : propertyMappers) {
      String key = mapper.getKey();
      SongPropertyMapper duplicate = this.propertyKeyMap.put(toLowerCase(key), mapper);
      if (duplicate != null) {
        throw new IllegalStateException("Duplicate property key: " + key);
      }
      duplicate = this.propertyNameMap.put(mapper.getName(), mapper);
      if (duplicate != null) {
        throw new IllegalStateException("Duplicate property name: " + key);
      }
    }
  }

  @Override
  public void format(Song song, MusicOutputStream out, SongFormatOptions options) {

    for (Property<?> property : song.getProperties()) {
      if (property == song.score) {
        continue;
      }
      String name = property.getName();
      SongPropertyMapper mapper = this.propertyNameMap.get(name);
      if (mapper == null) {
        // log warning
      } else {
        Object value = property.getValue();
        if (value != null) {
          formatPropertyKey(mapper, out, options);
          mapper.format(song, out, options);
          out.append(NEWLINE);
        }
      }
    }
    getScoreMapper().format(song.score.getValue(), out, options);
  }

  /**
   * @param mapper the {@link SongPropertyMapper}.
   * @param out the {@link MusicOutputStream}.
   * @param options the {@link SongFormatOptions}.
   */
  protected void formatPropertyKey(SongPropertyMapper mapper, MusicOutputStream out, SongFormatOptions options) {

    out.append(mapper.getKey());
    out.append(PROPERTIES_KEY_VALUE_SEPARATOR);
  }

  @Override
  public Song parse(MusicInputStream chars, SongFormatOptions options) {

    Song song = new Song();
    options.setSong(song);
    // TODO support property continuation (e.g. "+: ...")
    while (parseProperty(chars, options)) {
      // nothing else to do
    }
    Score score = getScoreMapper().parse(chars, options);
    song.score.setValue(score);
    return song;
  }

  private boolean parseProperty(MusicInputStream in, SongFormatOptions options) {

    String property = in.readPropertyStart();
    if (property != null) {
      SongPropertyMapper mapper = this.propertyKeyMap.get(toLowerCase(property));
      if (mapper == null) {
        String garbage = in.readPropertyValue();
        in.addError("Unknown property '" + property + "' (skipping value: " + garbage + ").");
      } else {
        mapper.parse(in, options);
      }
      in.expect(NEWLINE, true);
      return true;
    }
    return false;
  }

  /**
   * @return the {@link ScoreMapper}.
   */
  public abstract ScoreMapper getScoreMapper();

}
