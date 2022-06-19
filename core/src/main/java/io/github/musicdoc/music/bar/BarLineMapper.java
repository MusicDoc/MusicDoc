package io.github.musicdoc.music.bar;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.volta.Volta;
import io.github.musicdoc.music.volta.VoltaMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link BarLine}.
 */
public class BarLineMapper extends AbstractMapper<BarLine> {

  /** The singleton instance. */
  public static final BarLineMapper INSTANCE = new BarLineMapper();

  @Override
  public BarLine parse(CharStream chars) {

    BarLineType type = getBarLineTypeMapper().parse(chars);
    if (type == null) {
      return null;
    }
    Volta volta = getVoltaMapper().parse(chars);
    return new BarLine(type, volta);
  }

  /**
   * @return the {@link BarLineTypeMapper}.
   */
  protected static BarLineTypeMapper getBarLineTypeMapper() {

    return BarLineTypeMapper.INSTANCE;
  }

  /**
   * @return the {@link VoltaMapper}.
   */
  protected static VoltaMapper getVoltaMapper() {

    return VoltaMapper.INSTANCE;
  }

  @Override
  public void format(BarLine bar, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (bar == null) {
      return;
    }
    getBarLineTypeMapper().format(bar.getType(), buffer, options);
    getVoltaMapper().format(bar.getVolta(), buffer, options);
  }
}
