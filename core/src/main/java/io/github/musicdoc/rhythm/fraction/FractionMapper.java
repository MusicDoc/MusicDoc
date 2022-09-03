package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link Fraction}.
 *
 * @param <F> type of the {@link Fraction} to map.
 */
public abstract class FractionMapper<F extends Fraction> extends AbstractMapper<F> {

  private static final Integer UNIT_DEFAULT = Integer.valueOf(4);

  @Override
  public F read(MusicInputStream in, SongFormatContext context) {

    Integer beats = in.readInteger(3, false);
    if (beats == null) {
      return null;
    }
    Integer unit;
    if (in.expect(BEAT_SEPARATOR)) {
      unit = in.readInteger(3, false);
    } else {
      unit = getDefaultUnit(in, context);
    }
    return read(beats.intValue(), unit, in, context);
  }

  /**
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @return the default value for {@link Fraction#getUnit()}.
   */
  protected Integer getDefaultUnit(MusicInputStream in, SongFormatContext context) {

    return UNIT_DEFAULT;
  }

  /**
   * @param beats the {@link Fraction#getBeats() beats}.
   * @param unit the {@link Fraction#getUnit() unit}. Will be
   *        {@link #getDefaultUnit(MusicInputStream, SongFormatContext) default} if omitted.
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @return the parsed {@link Fraction}.
   */
  public abstract F read(int beats, Integer unit, MusicInputStream in, SongFormatContext context);

  @Override
  public void write(F fraction, MusicOutputStream out, SongFormatContext context) {

    int beats = fraction.getBeats();
    out.write(Integer.toString(beats));
    int unit = fraction.getUnit();
    out.write(BEAT_SEPARATOR);
    out.write(Integer.toString(unit));
  }
}
