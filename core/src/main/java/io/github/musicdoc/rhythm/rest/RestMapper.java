package io.github.musicdoc.rhythm.rest;

import java.util.List;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.rhythm.value.MusicalValue;

/**
 * {@link io.github.musicdoc.format.AbstractMapper Mapper} for {@link Rest}.
 */
public abstract class RestMapper extends AbstractValuedItemMapper<Rest> {

  @Override
  protected Rest readItem(MusicInputStream in, SongFormatContext context, List<MusicalDecoration> decorations) {

    CharStreamScanner scanner = in.getScanner();
    char c = scanner.peek();
    boolean invisible;
    if (c == REST_VISIBLE) {
      invisible = false;
    } else if (c == REST_INVISIBLE) {
      invisible = true;
    } else {
      return null;
    }
    scanner.next();
    MusicalValue value = getValueMapper().read(in, context);
    return new Rest(value, invisible, decorations);
  }

  @Override
  protected void writeItem(Rest item, MusicOutputStream out, SongFormatContext context) {

    if (item == null) {
      return;
    }
    if (item.isInvisible()) {
      out.write(REST_INVISIBLE);
    } else {
      out.write(REST_VISIBLE);
    }
  }
}
