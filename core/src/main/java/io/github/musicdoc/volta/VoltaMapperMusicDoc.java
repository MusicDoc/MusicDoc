package io.github.musicdoc.volta;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link VoltaMapper} for {@link SongFormatMusicDoc}.
 */
public class VoltaMapperMusicDoc extends VoltaMapper {

  /** The singleton instance. */
  public static final VoltaMapperMusicDoc INSTANCE = new VoltaMapperMusicDoc();

  /**
   * The constructor.
   */
  protected VoltaMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
