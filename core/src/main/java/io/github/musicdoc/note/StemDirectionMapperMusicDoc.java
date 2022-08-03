package io.github.musicdoc.note;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link StemDirectionMapper} for {@link SongFormatMusicDoc}.
 */
public class StemDirectionMapperMusicDoc extends StemDirectionMapper {

  /** The singleton instance. */
  public static final StemDirectionMapperMusicDoc INSTANCE = new StemDirectionMapperMusicDoc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
