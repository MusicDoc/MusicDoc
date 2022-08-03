package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link BarLineMapper} for {@link SongFormatMusicDoc}.
 */
public class BarLineMapperMusicDoc extends BarLineMapper {

  /** The singleton instance. */
  public static final BarLineMapperMusicDoc INSTANCE = new BarLineMapperMusicDoc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
