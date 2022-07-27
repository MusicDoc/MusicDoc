package io.github.musicdoc.music.stave.voice;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.stave.Stave;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getOctaveShift() octaveShift}.
 */
public class StaveVoicePropertyMapperStaveLines extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperStaveLines(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_OCTAVE_SHIFT;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    int staveLines = Integer.parseInt(value);
    Stave stave = voice.getStave();
    assert (stave != null);
    assert (stave.isMutable());
    stave.setLines(staveLines);
    return voice;
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    int octaveShift = voice.getOctaveShift();
    if (octaveShift != 0) {
      out.writeProperty(getKey(), Integer.toString(octaveShift));
    }
  }

}
