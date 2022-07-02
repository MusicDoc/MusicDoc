package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValue}.
 */
public abstract class MusicalValueMapper extends AbstractMapper<MusicalValue> {

  private static final String BEAT_SEPRATOR_STRING = Character.toString(BEAT_SEPARATOR);

  @Override
  public MusicalValue parse(MusicInputStream chars, SongFormatOptions options) {

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
    MusicalValueVariation variation = getVariationMapper().parse(chars, options);
    return new MusicalValue(beats, fraction, variation);
  }

  /**
   * @return the {@link MusicalValueVariationMapper}.
   */
  protected abstract MusicalValueVariationMapper getVariationMapper();

  @Override
  public void format(MusicalValue value, MusicOutputStream out, SongFormatOptions options) {

    if (value == null) {
      return;
    }
    int beats = value.getBeats();
    if (beats > 1) {
      out.append(Integer.toString(beats));
      out.append(BEAT_SEPARATOR);
    }
    out.append(Integer.toString(value.getFraction()));
    getVariationMapper().format(value.getVariation(), out, options);
  }
}
