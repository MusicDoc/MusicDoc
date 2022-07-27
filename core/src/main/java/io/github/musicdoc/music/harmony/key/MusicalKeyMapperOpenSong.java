package io.github.musicdoc.music.harmony.key;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link MusicalKeyMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalKeyMapperOpenSong extends MusicalKeyMapper {

  /** The singleton instance. */
  public static final MusicalKeyMapperOpenSong INSTANCE = new MusicalKeyMapperOpenSong();

  /**
   * The constructor.
   */
  protected MusicalKeyMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
