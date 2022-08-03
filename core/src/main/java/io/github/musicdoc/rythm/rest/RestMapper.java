package io.github.musicdoc.rythm.rest;

import java.util.List;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.rythm.value.MusicalValue;

/**
 * {@link io.github.musicdoc.format.AbstractMapper Mapper} for {@link Rest}.
 */
public abstract class RestMapper extends AbstractValuedItemMapper<Rest> {

  @Override
  protected Rest readItem(MusicInputStream in, SongFormatContext context, List<MusicalDecoration> decorations) {

    char c = in.peek();
    boolean invisible;
    if (c == REST_VISIBLE) {
      invisible = false;
    } else if (c == REST_INVISIBLE) {
      invisible = true;
    } else {
      return null;
    }
    in.next();
    MusicalValue value = getValueMapper().read(in, context);
    if (value == null) {
      value = MusicalValue._1_1;
    }
    return new Rest(value, invisible, decorations);
  }

  @Override
  protected void writeItem(Rest item, MusicOutputStream out, SongFormatContext context) {

    if (item == null) {
      return;
    }
    out.write(item.getSymbol());
  }
}
