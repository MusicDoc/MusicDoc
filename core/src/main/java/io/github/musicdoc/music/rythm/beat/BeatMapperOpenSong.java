package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
