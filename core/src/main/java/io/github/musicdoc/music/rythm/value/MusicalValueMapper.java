package io.github.musicdoc.music.rythm.value;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValue}.
 */
public class MusicalValueMapper extends AbstractMapper<MusicalValue> {

  /** The singleton instance. */
  public static final MusicalValueMapper INSTANCE = new MusicalValueMapper();

  private static final String BEAT_SEPRATOR_STRING = Character.toString(BEAT_SEPARATOR);

  @Override
  public MusicalValue parse(CharStream chars) {

    Integer beatsInt = chars.readInteger(2, false);
    int beats = 1;
    if (beatsInt != null) {
      beats = beatsInt.intValue();
    }
    Integer fractionInt = null;
    int fraction = 4;
    if (chars.expect(BEAT_SEPRATOR_STRING, false)) {
      fractionInt = chars.readInteger(4, false);
    } else if (beatsInt == null) {
      return null;
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
    MusicalValueVariation variation = MusicalValueVariationMapper.INSTANCE.parse(chars);
    return new MusicalValue(beats, fraction, variation);
  }

  @Override
  public void format(MusicalValue value, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (value == null) {
      return;
    }
    int beats = value.getBeats();
    if (beats > 1) {
      buffer.append(Integer.toString(beats));
      buffer.append(BEAT_SEPARATOR);
    }
    buffer.append(Integer.toString(value.getFraction()));
    MusicalValueVariationMapper.INSTANCE.format(value.getVariation(), buffer, options);
  }
}
