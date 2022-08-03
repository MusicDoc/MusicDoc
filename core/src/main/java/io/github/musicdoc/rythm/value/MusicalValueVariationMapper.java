package io.github.musicdoc.rythm.value;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValueVariation}.
 */
public abstract class MusicalValueVariationMapper extends AbstractMapper<MusicalValueVariation> {

  @Override
  public MusicalValueVariation read(MusicInputStream in, SongFormatContext context) {

    if (in.expect(MusicalValueVariation.DOUBLE_PUNCTURED.toString(), false)) {
      return MusicalValueVariation.DOUBLE_PUNCTURED;
    } else if (in.expect(MusicalValueVariation.PUNCTURED.toString(), false)) {
      return MusicalValueVariation.PUNCTURED;
    } else if (in.expect(MusicalValueVariation.TRIPLET.toString(), false)) {
      return MusicalValueVariation.TRIPLET;
    }
    return MusicalValueVariation.NONE;
  }

  @Override
  public void write(MusicalValueVariation variation, MusicOutputStream out, SongFormatContext context) {

    if (variation == null) {
      return;
    }
    out.write(variation.toString());
  }
}
