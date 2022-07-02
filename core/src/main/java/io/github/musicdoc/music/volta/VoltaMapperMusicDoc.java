package io.github.musicdoc.music.volta;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
