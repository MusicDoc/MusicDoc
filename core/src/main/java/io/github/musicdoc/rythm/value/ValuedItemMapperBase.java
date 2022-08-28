package io.github.musicdoc.rythm.value;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.decoration.SlurDecoration;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rythm.rest.Rest;

/**
 * {@link AbstractMapper Mapper} for {@link ValuedItem}.
 */
public abstract class ValuedItemMapperBase extends ValuedItemMapper {

  @Override
  public ValuedItem<?> read(MusicInputStream in, SongFormatContext context) {

    MusicalDecoration decoration = readItemPrefix(in, context);
    ValuedItem<?> item = getNoteMapper().read(in, context);
    if (item == null) {
      item = getRestMapper().read(in, context);
    }
    if (item == null) {
      assert (decoration == null);
    } else {
      if (decoration != null) {
        item.getDecorations().add(decoration);
      }
      item = readItemSuffix(item, in, context);
    }
    return item;
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link MusicalDecoration} to add or {@code null} for none.
   */
  protected MusicalDecoration readItemPrefix(MusicInputStream in, SongFormatContext context) {

    if (in.expect('(')) {
      return SlurDecoration.SLUR_START;
    }
    return null;
  }

  /**
   * @param item the {@link ValuedItem} that has been parsed so far and may be decorated.
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the modified {@link ValuedItem}.
   */
  protected ValuedItem<?> readItemSuffix(ValuedItem<?> item, MusicInputStream in, SongFormatContext context) {

    if (in.expect(')')) {
      item.getDecorations().add(SlurDecoration.SLUR_END);
    }
    return item;
  }

  @Override
  public void write(ValuedItem<?> item, MusicOutputStream out, SongFormatContext context) {

    if (item instanceof Note) {
      getNoteMapper().write((Note) item, out, context);
    } else if (item instanceof Rest) {
      getRestMapper().write((Rest) item, out, context);
    }
  }
}
