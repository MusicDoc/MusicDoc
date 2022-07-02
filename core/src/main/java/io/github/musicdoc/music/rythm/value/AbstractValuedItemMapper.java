package io.github.musicdoc.music.rythm.value;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper} for {@link ValuedItem}.
 *
 * @param <I> type of the {@link ValuedItem} to map.
 */
public abstract class AbstractValuedItemMapper<I extends ValuedItem<?>> extends AbstractMapper<I> {

  @Override
  public I parse(MusicInputStream chars, SongFormatOptions options) {

    List<MusicalDecoration> decorations = new ArrayList<>();
    while (true) {
      MusicalDecoration decoration = getDecorationMapper().parse(chars, options);
      if (decoration == null) {
        break;
      }
      assert (!decoration.isItemSuffix());
      decorations.add(decoration);
    }
    I item = parseItem(chars, options, decorations);
    while (true) {
      MusicalDecoration decoration = getDecorationMapper().parse(chars, options);
      if (decoration == null) {
        break;
      }
      assert (decoration.isItemSuffix());
      decorations.add(decoration);
    }
    return item;
  }

  /**
   * @return the {@link MusicalDecorationMapper}.
   */
  protected abstract MusicalDecorationMapper getDecorationMapper();

  /**
   * @param chars the {@link MusicInputStream} to parse.
   * @param options the {@link SongFormatOptions}.
   * @param decorations the {@link List} of {@link MusicalDecoration}s.
   * @return the parsed item.
   */
  protected abstract I parseItem(MusicInputStream chars, SongFormatOptions options,
      List<MusicalDecoration> decorations);

  /**
   * @return the {@link MusicalValueMapper}.
   */
  protected abstract MusicalValueMapper getValueMapper();

  @Override
  public void format(I item, MusicOutputStream out, SongFormatOptions options) {

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
        getDecorationMapper().format(decoration, out, options);
      }
    }
    formatItem(item, out, options);
    getValueMapper().format(item.getValue(), out, options);
    // decoration suffixes
    if (suffixCount == 1) {
      getDecorationMapper().format(suffix, out, options);
    } else if (suffixCount > 1) {
      for (MusicalDecoration decoration : item.getDecorations()) {
        if (decoration.isItemSuffix()) {
          getDecorationMapper().format(decoration, out, options);
        }
      }
    }
  }

  /**
   * @param item {@link ValuedItem} to format.
   * @param out the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the formatted output.
   * @param options the {@link SongFormatOptions}.
   */
  protected abstract void formatItem(I item, MusicOutputStream out, SongFormatOptions options);

}