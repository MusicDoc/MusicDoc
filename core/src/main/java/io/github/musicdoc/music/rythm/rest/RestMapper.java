package io.github.musicdoc.music.rythm.rest;

import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.value.MusicalValue;

/**
 * {@link io.github.musicdoc.music.format.AbstractMapper Mapper} for {@link Rest}.
 */
public abstract class RestMapper extends AbstractValuedItemMapper<Rest> {

  @Override
  protected Rest parseItem(MusicInputStream chars, SongFormatOptions options, List<MusicalDecoration> decorations) {

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
    MusicalValue value = getValueMapper().parse(chars, options);
    if (value == null) {
      value = MusicalValue._1_1;
    }
    return new Rest(value, invisible, decorations);
  }

  @Override
  protected void formatItem(Rest item, MusicOutputStream out, SongFormatOptions options) {

    if (item == null) {
      return;
    }
    out.append(item.getSymbol());
  }
}
