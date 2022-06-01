package io.github.musicdoc.music.clef;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.ChromaticInterval;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Clef}.
 */
public class ClefMapper extends AbstractMapper<Clef> {

  public static final ClefMapper INSTANCE = new ClefMapper();

  @Override
  public Clef parse(CharStream chars) {

    char c = chars.peek();
    ClefSymbol type = ClefSymbol.valueOf("" + Character.toUpperCase(c));
    if (type == null) {
      return null;
    }
    chars.next();
    c = chars.peek();
    int shift = 0;
    if ((c == '+') || (c == '-')) {
      chars.next();
      Integer i = chars.readInteger();
      if (i == null) {
        throw new IllegalArgumentException("" + type + c);
      }
      shift = i.intValue();
      if (c == '-') {
        shift = -shift;
      }
    }
    return Clef.of(type, ChromaticInterval.of(shift));
  }

  @Override
  public void format(Clef clef, Appendable buffer, SongFormatOptions options) throws IOException {

    if (clef == null) {
      return;
    }
    buffer.append(clef.toString());
  }
}
