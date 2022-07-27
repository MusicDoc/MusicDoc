package io.github.musicdoc.music.stave.voice;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.note.StemDirection;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getStemDirection() stemDirection}.
 */
public class StaveVoicePropertyMapperStemDirection extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperStemDirection(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_STEM_DIRECTION;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    StemDirection stemDirection = getStemDirectionMapper().parseValue(value, context);
    return voice.setStemDirection(stemDirection);
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    StemDirection value = voice.getStemDirection();
    if ((value == StemDirection.AUTO) || (value == null)) {
      return; // omit default...
    }
    String string = getStemDirectionMapper().formatValue(value, context);
    if (string != null) {
      out.writeProperty(getKey(), string);
    }
  }

}
