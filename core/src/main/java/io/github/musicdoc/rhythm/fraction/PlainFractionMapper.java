package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;

/**
 * {@link AbstractMapper Mapper} for {@link PlainFraction}.
 */
public abstract class PlainFractionMapper extends FractionMapper<PlainFraction> {

  @Override
  public PlainFraction read(int beats, Integer unit, MusicInputStream in, SongFormatContext context) {

    return PlainFraction.of(beats, unit.intValue()).normalize();
  }

}
