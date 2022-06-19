package io.github.musicdoc.music.bar;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link BarLineType}.
 */
public class BarLineTypeMapper extends AbstractMapper<BarLineType> {

  /** The singleton instance. */
  public static final BarLineTypeMapper INSTANCE = new BarLineTypeMapper();

  @Override
  public BarLineType parse(CharStream chars) {

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
  public void format(BarLineType barType, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (barType == null) {
      return;
    }
    buffer.append(barType.getSymbol());
  }
}
