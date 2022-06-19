package io.github.musicdoc.music.rythm.value;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.rythm.rest.Rest;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link ValuedItem}.
 */
public class ValuedItemMapper extends AbstractMapper<ValuedItem<?>> {

  /** The singleton instance. */
  public static final ValuedItemMapper INSTANCE = new ValuedItemMapper();

  @Override
  public ValuedItem<?> parse(CharStream chars) {

    Note note = NoteMapper.INSTANCE.parse(chars);
    if (note != null) {
      return note;
    }
    return RestMapper.INSTANCE.parse(chars);
  }

  @Override
  public void format(ValuedItem<?> item, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (item instanceof Note) {
      NoteMapper.INSTANCE.format((Note) item, buffer, options);
    } else if (item instanceof Rest) {
      RestMapper.INSTANCE.format((Rest) item, buffer, options);
    }
  }
}
