package io.github.musicdoc.music.decoration;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalDecoration}.
 */
public abstract class MusicalDecorationMapper extends AbstractMapper<MusicalDecoration> {

  // TODO: This is crappy and hardcoded - we should use MusicalDecoration instead take all keys that have only a single
  // char.
  private static final CharFilter DECORATION_SYMBOL_FILTER = new ListCharFilter('h', 'w', true).join('~', '.')
      .or(new ListCharFilter('H', 'W', true));

  private static final ListCharFilter DECORATION_STOP_FILTER = ListCharFilter.NEWLINE.join(DECORATION_END, ITEM_END, CHORD_END);

  @Override
  public MusicalDecoration read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    String name = null;
    if (c == DECORATION_START) {
      in.next();
      name = in.readUntil(DECORATION_STOP_FILTER, false);
      c = in.next();
      if (c != DECORATION_END) {
        // actually an error, but be tolerant
        // return null;
      }
    } else if (DECORATION_SYMBOL_FILTER.accept(c)) {
      c = in.next();
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
