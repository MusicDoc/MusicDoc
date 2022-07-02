package io.github.musicdoc.music.clef;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.interval.ChromaticInterval;

/**
 * {@link AbstractMapper Mapper} for {@link Clef}.
 */
public abstract class ClefMapper extends AbstractMapper<Clef> {

  @Override
  public Clef parse(MusicInputStream chars, SongFormatOptions options) {

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
  public void format(Clef clef, MusicOutputStream out, SongFormatOptions options) {

    if (clef == null) {
      return;
    }
    out.append(clef.toString());
  }
}
