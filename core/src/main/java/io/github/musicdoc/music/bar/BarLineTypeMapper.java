package io.github.musicdoc.music.bar;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link BarLineType}.
 */
public abstract class BarLineTypeMapper extends AbstractMapper<BarLineType> {

  @Override
  public BarLineType parse(MusicInputStream chars, SongFormatOptions options) {

    char c = chars.peek();
    if (c == '|') {
      chars.next();
      c = chars.peek();
      if (c == '|') {
        chars.next();
        return BarLineType.DOUBLE;
      } else if (c == ':') {
        chars.next();
        return BarLineType.REPEAT_START;
      } else if (c == '§') {
        chars.next();
        return BarLineType.THIN_THICK;
      } else {
        return BarLineType.SINGLE;
      }
    } else if (c == ':') {
      if (chars.expect("::", false) || chars.expect(":|:", false) || chars.expect(":||:", false)) {
        return BarLineType.REPEAT_END_START;
      } else if (chars.expect(":|", false)) {
        return BarLineType.REPEAT_END;
      }
    } else if (c == '§') {
      chars.next();
      c = chars.peek();
      if (c == '|') {
        chars.next();
        return BarLineType.THICK_THIN;
      } else if (c == '§') {
        chars.next();
        return BarLineType.THICK_THICK;
      } else {
        return BarLineType.THICK;
      }
    }
    return null;
  }

  @Override
  public void format(BarLineType barType, MusicOutputStream out, SongFormatOptions options) {

    if (barType == null) {
      return;
    }
    out.append(barType.getSymbol());
  }
}
