package io.github.musicdoc.rhythm.value;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.tuplet.Tuplet;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValue}.
 */
public abstract class MusicalValueMapperBase extends MusicalValueMapper {

  private static final String BEAT_SEPRATOR_STRING = Character.toString(BEAT_SEPARATOR);

  @Override
  public MusicalValue read(MusicInputStream in, SongFormatContext context) {

    PlainFraction unitNoteLength = context.getUnitNoteLength();
    Integer beatsInt = in.readInteger(2, false);
    int beats = unitNoteLength.getBeats();
    if (beatsInt != null) {
      beats = beatsInt.intValue() * beats;
    }
    Integer unitInt = null;
    int unit = unitNoteLength.getUnit();
    if (in.expect(BEAT_SEPRATOR_STRING, false)) {
      unitInt = in.readInteger(4, false);
    }
    if (unitInt != null) {
      unit = unitInt.intValue() * unit;
    }
    if ((beats > 1) && (unitNoteLength.getValue() != 1)) {
      PlainFraction fraction = PlainFraction.of(beats, unit).normalize();
      beats = fraction.getBeats();
      unit = fraction.getUnit();
    }
    Punctuation punctuation = getPunctuationMapper().read(in, context);
    Tuplet tuplet = getTupletMapper().read(in, context);

    if ((punctuation == null) && (beats == 3)) {
      beats = 1;
      unit = unit / 2;
      punctuation = Punctuation.P1;
    }
    return new MusicalValue(beats, unit, punctuation, tuplet);
  }

  @Override
  public void write(MusicalValue value, MusicOutputStream out, SongFormatContext context) {

    if (value == null) {
      return;
    }
    PlainFraction unitNoteLength = context.getUnitNoteLength();
    SimpleFraction<?> outValue = value.getPlain().copy().divide(unitNoteLength);
    Punctuation punctuation = value.getPunctuation();
    if (punctuation != null) {
      punctuation = normalizePunctuation(punctuation, outValue);
    }
    outValue = outValue.normalize();
    int beats = outValue.getBeats();
    int unit = outValue.getUnit();
    if (beats > 1) {
      out.write(Integer.toString(beats));
    }
    if (unit > 1) {
      out.write(BEAT_SEPARATOR);
      out.write(Integer.toString(unit));
    }
    getPunctuationMapper().write(punctuation, out, context);
    getTupletMapper().write(value.getTuplet(), out, context);
  }

  /**
   * @param punctuation the {@link Punctuation}. Not {@code null}.
   * @param outValue the {@link MusicalValue#getPlain() plain} value (with unitNoteLength applied) and therefore
   *        {@link SimpleFraction#isMutable() mutable}.
   * @return the {@link Punctuation} or {@code null} if normalized.
   */
  protected Punctuation normalizePunctuation(Punctuation punctuation, SimpleFraction<?> outValue) {

    return punctuation;
  }

}
