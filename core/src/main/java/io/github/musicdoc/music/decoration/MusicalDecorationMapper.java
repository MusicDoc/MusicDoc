package io.github.musicdoc.music.decoration;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link MusicalDecoration}.
 */
public abstract class MusicalDecorationMapper extends AbstractMapper<MusicalDecoration> {

  // TODO: This is crappy and hardcoded - we should use MusicalDecoration instead take all keys that have only a single
  // char.
  private static final CharFilter DECORATION_SYMBOL_FILTER = new ListCharFilter('h', 'w', true).join('~', '.')
      .or(new ListCharFilter('H', 'W', true));

  private static final ListCharFilter DECORATION_STOP_FILTER = ListCharFilter.NEWLINE.join(DECORATION_END, ITEM_END,
      CHORD_END);

  @Override
  public MusicalDecoration parse(MusicInputStream chars, SongFormatOptions options) {

    char c = chars.peek();
    String name = null;
    if (c == DECORATION_START) {
      chars.next();
      name = chars.readUntil(DECORATION_STOP_FILTER, false);
      c = chars.next();
      if (c != DECORATION_END) {
        // actually an error, but be tolerant
        // return null;
      }
    } else if (DECORATION_SYMBOL_FILTER.accept(c)) {
      c = chars.next();
      name = Character.toString(c);
    }
    return MusicalDecoration.of(name);
  }

  @Override
  public void format(MusicalDecoration decoration, MusicOutputStream out, SongFormatOptions options) {

    if (decoration == null) {
      return;
    }
    if (options.isNormalizeItemDecorations()) {
      decoration = decoration.getReference();
    }
    String name = decoration.getName();
    if (name.length() > 1) {
      out.append(DECORATION_START);
      out.append(name);
      out.append(DECORATION_END);
    } else {
      out.append(name);
    }
  }
}
