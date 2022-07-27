package io.github.musicdoc.music.bar;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link AbstractMapper Mapper} for {@link BarLineType}.
 */
public abstract class BarLineTypeMapper extends AbstractMapper<BarLineType> {

  @Override
  public BarLineType read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    if (c == '|') {
      in.next();
      c = in.peek();
      if (c == '|') {
        in.next();
        return BarLineType.DOUBLE;
      } else if (c == ':') {
        in.next();
        return BarLineType.REPEAT_START;
      } else if (c == ']') {
        in.next();
        return BarLineType.THIN_THICK;
      } else {
        return BarLineType.SINGLE;
      }
    } else if (c == ':') {
      if (in.expect("::", false) || in.expect(":|:", false) || in.expect(":||:", false)) {
        return BarLineType.REPEAT_END_START;
      } else if (in.expect(":|", false)) {
        return BarLineType.REPEAT_END;
      }
    } else if (c == '[') {
      String bar = in.peek(2);
      BarLineType type = BarLineType.of(bar);
      if (type != null) {
        in.skip(2);
        return type;
      }
    } else if (in.expect("][", false)) {
      return BarLineType.THICK;
    }
    return null;
  }

  @Override
  public void write(BarLineType barType, MusicOutputStream out, SongFormatContext context) {

    if (barType == null) {
      return;
    }
    out.write(barType.getSymbol());
  }
}
