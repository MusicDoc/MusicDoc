package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.rythm.rest.Rest;

/**
 * {@link AbstractMapper Mapper} for {@link ValuedItem}.
 */
public abstract class ValuedItemMapper extends AbstractMapper<ValuedItem<?>> {

  @Override
  public ValuedItem<?> read(MusicInputStream in, SongFormatContext context) {

    Note note = getNoteMapper().read(in, context);
    if (note != null) {
      return note;
    }
    return getRestMapper().read(in, context);
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
