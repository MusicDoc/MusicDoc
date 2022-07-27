package io.github.musicdoc.music.stave.voice;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getName() name}.
 */
public class StaveVoicePropertyMapperTranspose extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperTranspose(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_TRANSPOSE;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    int transpose = Integer.parseInt(value);
    return voice.setTranspose(transpose);
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    int transpose = voice.getTranspose();
    if (transpose != 0) {
      out.writeProperty(getKey(), Integer.toString(transpose));
    }
  }

}
