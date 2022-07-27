package io.github.musicdoc.music.stave.voice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link AbstractMapper Mapper} for {@link StaveVoice}.
 */
public abstract class StaveVoiceMapperProperties extends StaveVoiceMapper {

  private final Map<String, StaveVoicePropertyMapper> propertyKeyMap;

  private final List<StaveVoicePropertyMapper> propertyList;

  /**
   * The constructor.
   *
   * @param propertyMappers the {@link StaveVoicePropertyMapper}s to register.
   */
  public StaveVoiceMapperProperties(StaveVoicePropertyMapper... propertyMappers) {

    super();
    this.propertyKeyMap = new HashMap<>(propertyMappers.length);
    this.propertyList = new ArrayList<>(propertyMappers.length);
    Set<String> nameSet = new HashSet<>(propertyMappers.length);
    SongFormat format = getFormat();
    for (StaveVoicePropertyMapper mapper : propertyMappers) {
      mapper.format = format;
      StaveVoicePropertyMapper duplicate = this.propertyKeyMap.put(mapper.getKey(), mapper);
      if (duplicate != null) {
        throw new IllegalStateException("Duplicate key " + mapper.getKey());
      }
      if (nameSet.add(mapper.getName())) {
        this.propertyList.add(mapper);
      }
    }
  }

  @Override
  public StaveVoice read(MusicInputStream in, SongFormatContext context) {

    StaveVoice voice = readVoice(in, context);
    String property;
    do {
      property = in.readPropertyStart();
      StaveVoicePropertyMapper mapper = this.propertyKeyMap.get(property);
      if (mapper == null) {
        String value = in.readPropertyValue();
        in.addWarning("Ignoring unknown property (" + property + "=" + value + ").");
      } else {
        mapper.read(in, context);
      }
    } while (property != null);
    return voice;
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the pre-parsed {@link StaveVoice}.
   */
  protected StaveVoice readVoice(MusicInputStream in, SongFormatContext context) {

    StaveVoice voice = context.getStaveVoice();
    if (voice == null) {
      voice = new StaveVoice(null);
      context.setStaveVoice(voice);
    }
    return voice;
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    if (voice == null) {
      return;
    }
    for (StaveVoicePropertyMapper mapper : this.propertyList) {
      mapper.write(voice, out, context);
    }
  }
}
