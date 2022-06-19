package io.github.musicdoc.music.rythm.rest;

import java.io.IOException;
import java.util.List;

import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link io.github.musicdoc.format.AbstractMapper Mapper} for {@link Rest}.
 */
public class RestMapper extends AbstractValuedItemMapper<Rest> {

  /** The singleton instance. */
  public static final RestMapper INSTANCE = new RestMapper();

  @Override
  protected Rest parseItem(CharStream chars, List<MusicalDecoration> decorations) {

    char c = chars.peek();
    boolean invisible;
    if (c == REST_VISIBLE) {
      invisible = false;
    } else if (c == REST_INVISIBLE) {
      invisible = true;
    } else {
      return null;
    }
    chars.next();
    MusicalValue value = MusicalValueMapper.INSTANCE.parse(chars);
    if (value == null) {
      value = MusicalValue._1_1;
    }
    return new Rest(value, invisible, decorations);
  }

  @Override
  protected void formatItem(Rest item, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (item == null) {
      return;
    }
    buffer.append(item.getSymbol());
  }
}
