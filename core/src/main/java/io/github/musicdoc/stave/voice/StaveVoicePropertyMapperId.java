package io.github.musicdoc.stave.voice;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getId() key}.
 */
public class StaveVoicePropertyMapperId extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperId(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_ID;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    return voice.setId(value);
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    if (getKey().isEmpty()) {
      // no property in ABC (design flaw)
      out.write(voice.getId());
    } else {
      out.writeProperty(getKey(), voice.getId());
    }
  }

}
