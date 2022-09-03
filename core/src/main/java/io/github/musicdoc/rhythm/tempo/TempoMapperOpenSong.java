package io.github.musicdoc.rhythm.tempo;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

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
