package io.github.musicdoc.music.note;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link StemDirectionMapper} for {@link SongFormatAbc}.
 */
public class StemDirectionMapperAbc extends StemDirectionMapper {

  /** The singleton instance. */
  public static final StemDirectionMapperAbc INSTANCE = new StemDirectionMapperAbc();

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
