package io.github.musicdoc.rythm.value;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link MusicalValueVariationMapper} for {@link SongFormatAbc}.
 */
public class MusicalValueVariationMapperAbc extends MusicalValueVariationMapper {

  /** The singleton instance. */
  public static final MusicalValueVariationMapperAbc INSTANCE = new MusicalValueVariationMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalValueVariationMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public void write(MusicalValueVariation variation, MusicOutputStream out, SongFormatContext context) {

    if ((variation == null) || (variation.getPunctuationCount() > 0)) {
      return;
    }
    super.write(variation, out, context);
  }

}
