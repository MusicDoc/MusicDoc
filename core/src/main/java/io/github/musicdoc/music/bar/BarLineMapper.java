package io.github.musicdoc.music.bar;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.volta.Volta;
import io.github.musicdoc.music.volta.VoltaMapper;

/**
 * {@link AbstractMapper Mapper} for {@link BarLine}.
 */
public abstract class BarLineMapper extends AbstractMapper<BarLine> {

  @Override
  public BarLine parse(MusicInputStream chars, SongFormatOptions options) {

    BarLineType type = getBarLineTypeMapper().parse(chars, options);
    if (type == null) {
      return null;
    }
    Volta volta = getVoltaMapper().parse(chars, options);
    return new BarLine(type, volta);
  }

  /**
   * @return the {@link BarLineTypeMapper}.
   */
  protected abstract BarLineTypeMapper getBarLineTypeMapper();

  /**
   * @return the {@link VoltaMapper}.
   */
  protected abstract VoltaMapper getVoltaMapper();

  @Override
  public void format(BarLine bar, MusicOutputStream out, SongFormatOptions options) {

    if (bar == null) {
      return;
    }
    getBarLineTypeMapper().format(bar.getType(), out, options);
    getVoltaMapper().format(bar.getVolta(), out, options);
  }
}
