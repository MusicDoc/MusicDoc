package io.github.musicdoc.rythm.beat;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link Beat}.
 */
public abstract class BeatMapper extends AbstractMapper<Beat> {

  @Override
  public Beat read(MusicInputStream in, SongFormatContext context) {

    Integer beats = in.readInteger(3, false);
    if (beats == null) {
      if (in.expect(Beat.CUT_TIME.toString(), false)) {
        return Beat.CUT_TIME;
      } else if (in.expect(Beat.COMMON_TIME.toString(), false)) {
        return Beat.COMMON_TIME;
      } else if (in.expect(Beat.NONE.toString(), false)) {
        return Beat.NONE;
      } else {
        return null;
      }
    }
    int fract = 4;
    if (in.expect(BEAT_SEPARATOR)) {
      Integer fraction = in.readInteger(3, false);
      if (fraction != null) {
        fract = fraction.intValue();
      }
    }
    return Beat.of(beats.intValue(), fract);
  }

  @Override
  public void write(Beat beat, MusicOutputStream out, SongFormatContext context) {

    if (beat == null) {
      out.write("none");
    } else if (beat == Beat.COMMON_TIME) {
      out.write("C");
    } else if (beat == Beat.CUT_TIME) {
      out.write("C|");
    } else {
      int beats = beat.getBeats();
      out.write(Integer.toString(beats));
      int fraction = beat.getUnit();
      out.write(BEAT_SEPARATOR);
      out.write(Integer.toString(fraction));
    }
  }
}
