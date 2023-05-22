package io.github.musicdoc.decoration;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalDecoration}.
 */
public abstract class MusicalDecorationMapper extends AbstractMapper<MusicalDecoration> {

  // TODO: This is crappy and hardcoded - we should use MusicalDecoration instead take all keys that have only a single
  // char.
  private static final CharFilter DECORATION_SYMBOL_FILTER = new ListCharFilter("hwHW~.");

  private static final CharFilter DECORATION_STOP_FILTER = CharFilter.NEWLINE
      .compose(new ListCharFilter(DECORATION_END, ITEM_END, CHORD_END));

  @Override
  public MusicalDecoration read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    char c = scanner.peek();
    String name = null;
    if (c == DECORATION_START) {
      scanner.next();
      name = scanner.readUntil(DECORATION_STOP_FILTER, false);
      c = scanner.next();
      if (c != DECORATION_END) {
        // actually an error, but be tolerant
        // return null;
      }
    } else if (DECORATION_SYMBOL_FILTER.accept(c)) {
      c = scanner.next();
      name = Character.toString(c);
    }
    return MusicalDecoration.of(name);
  }

  @Override
  public void write(MusicalDecoration decoration, MusicOutputStream out, SongFormatContext context) {

    if (decoration == null) {
      return;
    }
    if (context.isNormalizeItemDecorations()) {
      decoration = decoration.getReference();
    }
    String name = decoration.getName();
    if (name.length() > 1) {
      out.write(DECORATION_START);
      out.write(name);
      out.write(DECORATION_END);
    } else {
      out.write(name);
    }
  }
}
