package io.github.musicdoc.music.note;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
