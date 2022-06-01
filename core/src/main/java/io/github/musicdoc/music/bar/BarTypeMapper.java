package io.github.musicdoc.music.bar;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link BarType}.
 */
public class BarTypeMapper extends AbstractMapper<BarType> {

  /** The singleton instance. */
  public static final BarTypeMapper INSTANCE = new BarTypeMapper();

  @Override
  public BarType parse(CharStream chars) {

    char c = chars.peek();
    if (c == '|') {
      chars.next();
      c = chars.peek();
      if (c == '|') {
        chars.next();
        return BarType.DOUBLE;
      } else if (c == ':') {
        chars.next();
        return BarType.REPEAT_START;
      } else if (c == '§') {
        chars.next();
        return BarType.THIN_THICK;
      } else {
        return BarType.SINGLE;
      }
    } else if (c == ':') {
      if (chars.expect("::", false) || chars.expect(":|:", false) || chars.expect(":||:", false)) {
        return BarType.REPEAT_END_START;
      } else if (chars.expect(":|", false)) {
        return BarType.REPEAT_END;
      }
    } else if (c == '§') {
      chars.next();
      c = chars.peek();
      if (c == '|') {
        chars.next();
        return BarType.THICK_THIN;
      } else if (c == '§') {
        chars.next();
        return BarType.THICK_THICK;
      } else {
        return BarType.THICK;
      }
    }
    return null;
  }

  @Override
  public void format(BarType barType, Appendable buffer, SongFormatOptions options) throws IOException {

    if (barType == null) {
      return;
    }
    buffer.append(barType.getSymbol());
  }
}
