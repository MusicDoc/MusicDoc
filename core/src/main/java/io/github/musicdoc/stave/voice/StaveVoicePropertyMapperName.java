package io.github.musicdoc.stave.voice;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getName() name}.
 */
public class StaveVoicePropertyMapperName extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperName(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_NAME;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    return voice.setName(value);
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    out.writeProperty(getKey(), voice.getName());
  }

}
