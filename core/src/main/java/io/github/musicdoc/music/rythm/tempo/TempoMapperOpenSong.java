package io.github.musicdoc.music.rythm.tempo;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;

/**
 * {@link TempoMapper} for {@link SongFormatOpenSong}.
 */
public class TempoMapperOpenSong extends TempoMapper {

  /** The singleton instance. */
  public static final TempoMapperOpenSong INSTANCE = new TempoMapperOpenSong();

  // TODO incompatible formats, OpenSong uses duration in seconds

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

}
