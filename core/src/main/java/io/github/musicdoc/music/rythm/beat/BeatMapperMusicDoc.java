package io.github.musicdoc.music.rythm.beat;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link BeatMapper} for {@link SongFormatOpenSong}.
 */
public class BeatMapperMusicDoc extends BeatMapper {

  /** The singleton instance. */
  public static final BeatMapperMusicDoc INSTANCE = new BeatMapperMusicDoc();

  /**
   * The constructor.
   */
  protected BeatMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
