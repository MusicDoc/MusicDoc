package io.github.musicdoc.rythm.beat;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link BeatMapper} for {@link SongFormatMusicDoc}.
 */
public class BeatMapperOpenSong extends BeatMapper {

  /** The singleton instance. */
  public static final BeatMapperOpenSong INSTANCE = new BeatMapperOpenSong();

  /**
   * The constructor.
   */
  protected BeatMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
