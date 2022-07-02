package io.github.musicdoc.music.instrument;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link Instrument}.
 */
public abstract class InstrumentMapper extends AbstractMapper<Instrument> {

  @Override
  public Instrument parse(MusicInputStream chars, SongFormatOptions options) {

    // TODO
    return null;
  }

  @Override
  public void format(Instrument instrument, MusicOutputStream out, SongFormatOptions options) {

    if (instrument == null) {
      return;
    }
    out.append(instrument.getName());
  }
}
