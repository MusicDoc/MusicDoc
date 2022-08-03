package io.github.musicdoc.stave.voice;

import java.util.List;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.io.PropertyState;
import io.github.musicdoc.io.TextMusicInputStream;
import io.github.musicdoc.stave.AbstractStave;
import io.github.musicdoc.stave.Stave;

/**
 * {@link StaveVoicePropertyMapper} for {@link AbstractStave#getClef() clef}.
 */
public class StaveVoicePropertyMapperClef extends StaveVoicePropertyMapper {

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public StaveVoicePropertyMapperClef(String key) {

    super(key);
  }

  @Override
  public String getName() {

    return AbstractStave.PROPERTY_CLEF;
  }

  @Override
  protected StaveVoice parseValue(StaveVoice voice, String value, MusicInputStream in, SongFormatContext context) {

    Clef clef = getClefMapper().read(new TextMusicInputStream(value, PropertyState.of()), context);
    Stave stave = voice.getStave();
    Stave newStave = stave.setClef(clef);
    assert (newStave == stave);
    return voice;
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    Stave stave = voice.getStave();
    if (stave == null) {
      return;
    }
    List<StaveVoice> voices = stave.getVoices();
    if (voices.isEmpty()) {
      return;
    }
    if ((voices.get(0) != voice) && (this.format != SongFormatAbc.INSTANCE)) {
      // omit redundant clef multiple voices in the same stave, however not for ABC that is flawed
      return;
    }
    Clef clef = stave.getClef();
    if (clef == null) {
      return;
    }
    String clefValue = getClefMapper().write(clef, context);
    out.writeProperty(getKey(), clefValue);
  }

}
