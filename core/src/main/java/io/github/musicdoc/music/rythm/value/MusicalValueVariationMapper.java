package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalValueVariation}.
 */
public abstract class MusicalValueVariationMapper extends AbstractMapper<MusicalValueVariation> {

  @Override
  public MusicalValueVariation parse(MusicInputStream chars, SongFormatOptions options) {

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
  public void format(MusicalValueVariation variation, MusicOutputStream out, SongFormatOptions options) {

    if (variation == null) {
      return;
    }
    out.append(variation.toString());
  }
}
