package io.github.musicdoc.music.format;

import io.github.musicdoc.music.song.SongMapper;
import io.github.musicdoc.music.song.SongMapperMusicDoc;

/**
 * {@link SongFormat} for our default format <a href="https://musicdoc.github.io/">MusicDoc</a>.
 */
public class SongFormatMusicDoc extends SongFormatText {

  /** The singleton instance. */
  public static final SongFormatMusicDoc INSTANCE = new SongFormatMusicDoc();

  private SongFormatMusicDoc() {

    super();
  }

  @Override
  public String getName() {

    return "MusicDoc";
  }

  @Override
  protected SongMapper getSongMapper() {

    return SongMapperMusicDoc.INSTANCE;
  }

}
