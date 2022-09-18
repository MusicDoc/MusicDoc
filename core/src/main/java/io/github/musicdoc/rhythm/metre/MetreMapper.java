package io.github.musicdoc.rhythm.metre;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link Metre}.
 */
public abstract class MetreMapper extends AbstractMapper<Metre> {

  @Override
  public Metre read(MusicInputStream in, SongFormatContext context) {

    Integer beats = in.readInteger(3, false);
    if (beats == null) {
      if (in.expect(Metre.CUT_TIME.toString(), false)) {
        return Metre.CUT_TIME;
      } else if (in.expect(Metre.COMMON_TIME.toString(), false)) {
        return Metre.COMMON_TIME;
      } else if (in.expect(Metre.NONE.toString(), false)) {
        return Metre.NONE;
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
    return Metre.of(beats.intValue(), fract);
  }

  @Override
  public void write(Metre beat, MusicOutputStream out, SongFormatContext context) {

    if (beat == null) {
      out.write("none");
    } else if (beat == Metre.COMMON_TIME) {
      out.write("C");
    } else if (beat == Metre.CUT_TIME) {
      out.write("C|");
    } else {
      int beats = beat.getBeats();
      out.write(Integer.toString(beats));
      int unit = beat.getUnit();
      out.write(BEAT_SEPARATOR);
      out.write(Integer.toString(unit));
    }
  }
}
