package io.github.musicdoc.stave.voice;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.stave.Stave;

/**
 * {@link StaveVoicePropertyMapper} for {@link StaveVoice#getOctaveShift() octaveShift}.
 */
public class StaveVoicePropertyMapperOctaveShift extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperOctaveShift(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return StaveVoice.PROPERTY_OCTAVE_SHIFT;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    int octaveShift = Integer.parseInt(value);
    Stave stave = voice.getStave();
    Clef clef = stave.getClef();
    assert (clef != null);
    assert (clef.isMutable());
    clef.setShiftAdd(ChromaticInterval.of(octaveShift * 12));
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
