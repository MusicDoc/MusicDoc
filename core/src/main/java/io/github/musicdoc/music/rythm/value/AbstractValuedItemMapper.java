package io.github.musicdoc.music.rythm.value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper} for {@link ValuedItem}.
 *
 * @param <I> type of the {@link ValuedItem} to map.
 */
public abstract class AbstractValuedItemMapper<I extends ValuedItem<?>> extends AbstractMapper<I> {

  @Override
  public I parse(CharStream chars) {

    List<MusicalDecoration> decorations = new ArrayList<>();
    while (true) {
      MusicalDecoration decoration = MusicalDecorationMapper.INSTANCE.parse(chars);
      if (decoration == null) {
        break;
      }
      assert (!decoration.isItemSuffix());
      decorations.add(decoration);
    }
    I item = parseItem(chars, decorations);
    while (true) {
      MusicalDecoration decoration = MusicalDecorationMapper.INSTANCE.parse(chars);
      if (decoration == null) {
        break;
      }
      assert (decoration.isItemSuffix());
      decorations.add(decoration);
    }
    return item;
  }

  /**
   * @param chars the {@link CharStream} to parse.
   * @param decorations the {@link List} of {@link MusicalDecoration}s.
   * @return the parsed item.
   */
  protected abstract I parseItem(CharStream chars, List<MusicalDecoration> decorations);

  @Override
  public void format(I item, Appendable buffer, MusicFormatOptions options) throws IOException {

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
        MusicalDecorationMapper.INSTANCE.format(decoration, buffer, options);
      }
    }
    formatItem(item, buffer, options);
    MusicalValueMapper.INSTANCE.format(item.getValue(), buffer, options);
    // decoration suffixes
    if (suffixCount == 1) {
      MusicalDecorationMapper.INSTANCE.format(suffix, buffer, options);
    } else if (suffixCount > 1) {
      for (MusicalDecoration decoration : item.getDecorations()) {
        if (decoration.isItemSuffix()) {
          MusicalDecorationMapper.INSTANCE.format(decoration, buffer, options);
        }
      }
    }
  }

  /**
   * @param item {@link ValuedItem} to format.
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the formatted output.
   * @param options the {@link MusicFormatOptions}.
   * @throws IOException in case of an input/output error.
   */
  protected abstract void formatItem(I item, Appendable buffer, MusicFormatOptions options) throws IOException;

}