package io.github.musicdoc.music.format;

import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.song.SongMapperOpenSong;

/**
 * {@link SongFormat} for OpenSoung.
 */
public class SongFormatOpenSong extends SongFormatXml {

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
  protected String getRootTag() {

    return "song";
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperOpenSong.INSTANCE;
  }

}
