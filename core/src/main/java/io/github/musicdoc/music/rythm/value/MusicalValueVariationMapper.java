package io.github.musicdoc.music.rythm.value;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValueVariation}.
 */
public class MusicalValueVariationMapper extends AbstractMapper<MusicalValueVariation> {

  /** The singleton instance. */
  public static final MusicalValueVariationMapper INSTANCE = new MusicalValueVariationMapper();

  @Override
  public MusicalValueVariation parse(CharStream chars) {

    if (chars.expect(MusicalValueVariation.DOUBLE_PUNCTURED.toString(), false)) {
      return MusicalValueVariation.DOUBLE_PUNCTURED;
    } else if (chars.expect(MusicalValueVariation.PUNCTURED.toString(), false)) {
      return MusicalValueVariation.PUNCTURED;
    } else if (chars.expect(MusicalValueVariation.TRIPLET.toString(), false)) {
      return MusicalValueVariation.TRIPLET;
    }
    return MusicalValueVariation.NONE;
  }

  @Override
  public void format(MusicalValueVariation variation, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (variation == null) {
      return;
    }
    buffer.append(variation.toString());
  }
}
