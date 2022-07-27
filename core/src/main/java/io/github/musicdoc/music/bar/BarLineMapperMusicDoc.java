package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
