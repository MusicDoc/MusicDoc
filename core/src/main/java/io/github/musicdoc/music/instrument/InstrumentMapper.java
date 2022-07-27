package io.github.musicdoc.music.instrument;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;

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
