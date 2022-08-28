package io.github.musicdoc.rythm.value;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rythm.fraction.PlainFraction;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValue}.
 */
public abstract class MusicalValueMapper extends AbstractMapper<MusicalValue> {

  private static final String BEAT_SEPRATOR_STRING = Character.toString(BEAT_SEPARATOR);

  @Override
  public MusicalValue read(MusicInputStream in, SongFormatContext context) {

    Integer beatsInt = in.readInteger(2, false);
    int beats = 1;
    if (beatsInt != null) {
      beats = beatsInt.intValue();
    }
    Integer fractionInt = null;
    int fraction = 4;
    boolean empty = false;
    if (in.expect(BEAT_SEPRATOR_STRING, false)) {
      fractionInt = in.readInteger(4, false);
    } else if (beatsInt == null) {
      empty = true;
    }
    if (fractionInt == null) {
      if (beats == 2) {
        beats = 1;
        fraction = 2;
      } else if (beats == 4) {
        beats = 4;
        fraction = 4;
      }
    } else {
      fraction = fractionInt.intValue();
      if (beatsInt == null) {
        fraction = fraction * 4;
      }
    }
    MusicalValueVariation variation = getVariationMapper().read(in, context);
    if (empty && (variation == null)) {
      return null;
    }
    if ((variation == MusicalValueVariation.NONE) && (beats == 3)) {
      beats = 1;
      fraction = fraction / 2;
      variation = MusicalValueVariation.PUNCTURED;
    }
    return new MusicalValue(beats, fraction, variation);
  }

  @Override
  public void write(MusicalValue value, MusicOutputStream out, SongFormatContext context) {

    if (value == null) {
      return;
    }
    PlainFraction unitNoteLength = context.getUnitNoteLength();
    PlainFraction outValue = value.asPlain().divide(unitNoteLength);
    MusicalValueVariation variation = value.getVariation();
    if ((variation != MusicalValueVariation.NONE) && !isSupportVariation()) {
      outValue = outValue.multiply(variation);
      variation = MusicalValueVariation.NONE;
    }
    outValue = outValue.normalize();
    int beats = outValue.getBeats();
    int fraction = outValue.getUnit();
    if (beats > 1) {
      out.write(Integer.toString(beats));
    }
    if (fraction > 1) {
      out.write(BEAT_SEPARATOR);
      out.write(Integer.toString(fraction));
    }
    getVariationMapper().write(variation, out, context);
  }

  /**
   * @return {@code true} if {@link MusicalValueVariation} is supported as specific syntax of this format, {@code false}
   *         otherwise.
   */
  protected boolean isSupportVariation() {

    return true;
  }
}
