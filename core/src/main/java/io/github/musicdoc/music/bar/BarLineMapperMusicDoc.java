package io.github.musicdoc.music.bar;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.volta.VoltaMapper;
import io.github.musicdoc.music.volta.VoltaMapperMusicDoc;

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

  @Override
  protected BarLineTypeMapper getBarLineTypeMapper() {

    return BarLineTypeMapperMusicDoc.INSTANCE;
  }

  @Override
  protected VoltaMapper getVoltaMapper() {

    return VoltaMapperMusicDoc.INSTANCE;
  }

}
