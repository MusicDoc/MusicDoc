package io.github.musicdoc.instrument;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link Instrument}.
 */
public abstract class InstrumentMapper extends AbstractMapper<Instrument> {

  @Override
  public Instrument read(MusicInputStream in, SongFormatContext context) {

    // TODO
    return null;
  }

  @Override
  public void write(Instrument instrument, MusicOutputStream out, SongFormatContext context) {

    if (instrument == null) {
      return;
    }
    out.write(instrument.getName());
  }
}
