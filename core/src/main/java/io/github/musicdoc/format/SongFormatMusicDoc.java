package io.github.musicdoc.format;

import io.github.musicdoc.music.partiture.PartitureMapper;

/**
 * {@link SongFormat} for our default format <a href="https://musicdoc.github.io/">MusicDoc</a>.
 */
public class SongFormatMusicDoc extends SongFormat {

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
  protected PartitureMapper getPartitureMapper() {

    return PartitureMapper.MUSIC_DOC;
  }

}
