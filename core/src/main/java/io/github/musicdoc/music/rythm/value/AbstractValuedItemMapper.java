package io.github.musicdoc.music.rythm.value;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;

/**
 * {@link AbstractMapper} for {@link ValuedItem}.
 *
 * @param <I> type of the {@link ValuedItem} to map.
 */
public abstract class AbstractValuedItemMapper<I extends ValuedItem<?>> extends AbstractMapper<I> {

  @Override
  public I read(MusicInputStream in, SongFormatContext context) {

    List<MusicalDecoration> decorations = new ArrayList<>();
    while (true) {
      MusicalDecoration decoration = getDecorationMapper().read(in, context);
      if (decoration == null) {
        break;
      }
      assert (!decoration.isItemSuffix());
      decorations.add(decoration);
    }
    I item = readItem(in, context, decorations);
    while (true) {
      MusicalDecoration decoration = getDecorationMapper().read(in, context);
      if (decoration == null) {
        break;
      }
      assert (decoration.isItemSuffix());
      decorations.add(decoration);
    }
    return item;
  }

  /**
   * @param in the {@link MusicInputStream} to parse.
   * @param context the {@link SongFormatContext}.
   * @param decorations the {@link List} of {@link MusicalDecoration}s.
   * @return the parsed item.
   */
  protected abstract I readItem(MusicInputStream in, SongFormatContext context, List<MusicalDecoration> decorations);

  @Override
  public void write(I item, MusicOutputStream out, SongFormatContext context) {

    if (item == null) {
      return;
    }
    // decoration prefixes
    MusicalDecoration suffix = null;
    int suffixCount = 0;
    for (MusicalDecoration decoration : item.getDecorations()) {
      if (decoration.isItemSuffix()) {
        suffix = decoration;
        suffixCount++;
      } else {
        getDecorationMapper().write(decoration, out, context);
      }
    }
    writeItem(item, out, context);
    getValueMapper().write(item.getValue(), out, context);
    // decoration suffixes
    if (suffixCount == 1) {
      getDecorationMapper().write(suffix, out, context);
    } else if (suffixCount > 1) {
      for (MusicalDecoration decoration : item.getDecorations()) {
        if (decoration.isItemSuffix()) {
          getDecorationMapper().write(decoration, out, context);
        }
      }
    }
  }

  /**
   * @param item {@link ValuedItem} to format.
   * @param out the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the formatted output.
   * @param context the {@link SongFormatContext}.
   */
  protected abstract void writeItem(I item, MusicOutputStream out, SongFormatContext context);

}