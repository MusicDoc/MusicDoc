package io.github.musicdoc.music.harmony;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link TonalSystemMapper} for {@link SongFormatMusicDoc}.
 */
public class TonalSystemMapperMusicDoc extends TonalSystemMapper {

  /** The singleton instance. */
  public static final TonalSystemMapperMusicDoc INSTANCE = new TonalSystemMapperMusicDoc();

  /**
   * The constructor.
   */
  protected TonalSystemMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
