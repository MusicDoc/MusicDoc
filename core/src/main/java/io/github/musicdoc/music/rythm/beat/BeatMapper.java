package io.github.musicdoc.music.rythm.beat;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Beat}.
 */
public class BeatMapper extends AbstractMapper<Beat> {

  /** The singleton instance. */
  public static final BeatMapper INSTANCE = new BeatMapper();

  @Override
  public Beat parse(CharStream chars) {

    Integer beats = chars.readInteger(3, false);
    if (beats == null) {
      return null;
    }
    int fract = 4;
    if (chars.expect(BEAT_SEPARATOR)) {
      Integer fraction = chars.readInteger(3, false);
      if (fraction != null) {
        fract = fraction.intValue();
      }
    }
    return Beat.of(beats.intValue(), fract);
  }

  @Override
  public void format(Beat beat, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (beat == null) {
      return;
    }
    int beats = beat.getBeats();
    buffer.append(Integer.toString(beats));
    int fraction = beat.getFraction();
    if (fraction != 4) {
      buffer.append(BEAT_SEPARATOR);
      buffer.append(Integer.toString(fraction));
    }
  }
}
