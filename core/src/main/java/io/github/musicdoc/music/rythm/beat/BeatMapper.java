package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link Beat}.
 */
public abstract class BeatMapper extends AbstractMapper<Beat> {

  @Override
  public Beat parse(MusicInputStream chars, SongFormatOptions options) {

    Integer beats = chars.readInteger(3, false);
    if (beats == null) {
      if (chars.expect(Beat.CUT_TIME.toString(), false)) {
        return Beat.CUT_TIME;
      } else if (chars.expect(Beat.COMMON_TIME.toString(), false)) {
        return Beat.COMMON_TIME;
      } else if (chars.expect(Beat.NONE.toString(), false)) {
        return Beat.NONE;
      } else {
        String garbage = chars.readUntil(NEWLINE_CHAR, false);
        chars.addError("Undefined beat: " + garbage);
        return null;
      }
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
  public void format(Beat beat, MusicOutputStream out, SongFormatOptions options) {

    if (beat == null) {
      out.append("none");
    } else if (beat == Beat.COMMON_TIME) {
      out.append("C");
    } else if (beat == Beat.CUT_TIME) {
      out.append("C|");
    } else {
      int beats = beat.getBeats();
      out.append(Integer.toString(beats));
      int fraction = beat.getFraction();
      if (fraction != 4) {
        out.append(BEAT_SEPARATOR);
        out.append(Integer.toString(fraction));
      }
    }
  }
}
