package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.rythm.rest.Rest;
import io.github.musicdoc.music.rythm.rest.RestMapper;

/**
 * {@link AbstractMapper Mapper} for {@link ValuedItem}.
 */
public abstract class ValuedItemMapper extends AbstractMapper<ValuedItem<?>> {

  @Override
  public ValuedItem<?> parse(MusicInputStream chars, SongFormatOptions options) {

    Note note = getNoteMapper().parse(chars, options);
    if (note != null) {
      return note;
    }
    return getRestMapper().parse(chars, options);
  }

  /**
   * @return the {@link RestMapper}.
   */
  protected abstract RestMapper getRestMapper();

  /**
   * @return the {@link NoteMapper}.
   */
  protected abstract NoteMapper getNoteMapper();

  @Override
  public void format(ValuedItem<?> item, MusicOutputStream out, SongFormatOptions options) {

    if (item instanceof Note) {
      getNoteMapper().format((Note) item, out, options);
    } else if (item instanceof Rest) {
      getRestMapper().format((Rest) item, out, options);
    }
  }
}
