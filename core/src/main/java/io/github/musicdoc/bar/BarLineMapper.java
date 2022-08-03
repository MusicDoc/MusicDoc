package io.github.musicdoc.bar;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.volta.Volta;

/**
 * {@link AbstractMapper Mapper} for {@link BarLine}.
 */
public abstract class BarLineMapper extends AbstractMapper<BarLine> {

  @Override
  public BarLine read(MusicInputStream in, SongFormatContext context) {

    BarLineType type = getBarLineTypeMapper().read(in, context);
    if (type == null) {
      return null;
    }
    Volta volta = getVoltaMapper().read(in, context);
    return new BarLine(type, volta);
  }

  @Override
  public void write(BarLine bar, MusicOutputStream out, SongFormatContext context) {

    if (bar == null) {
      return;
    }
    getBarLineTypeMapper().write(bar.getType(), out, context);
    getVoltaMapper().write(bar.getVolta(), out, context);
  }
}
