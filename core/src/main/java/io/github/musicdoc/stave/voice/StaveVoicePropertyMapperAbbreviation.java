package io.github.musicdoc.stave.voice;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getAbbreviation() abbreviation}.
 */
public class StaveVoicePropertyMapperAbbreviation extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperAbbreviation(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_ABBREVIATION;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    return voice.setAbbreviation(value);
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    out.writeProperty(getKey(), voice.getAbbreviation());
  }

}
