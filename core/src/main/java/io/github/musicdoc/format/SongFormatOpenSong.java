package io.github.musicdoc.format;

import io.github.musicdoc.music.partiture.PartitureMapper;

/**
 * {@link SongFormat} for OpenSoung.
 */
public class SongFormatOpenSong extends SongFormat {

  /** The singleton instance. */
  public static final SongFormatOpenSong INSTANCE = new SongFormatOpenSong();

  private SongFormatOpenSong() {

    super();
  }

  @Override
  public String getName() {

    return "OpenSong";
  }

  @Override
  protected PartitureMapper getPartitureMapper() {

    return PartitureMapper.OPEN_SONG;
  }

}
