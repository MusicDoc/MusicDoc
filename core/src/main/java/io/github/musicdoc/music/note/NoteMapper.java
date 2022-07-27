package io.github.musicdoc.music.note;

import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.tone.Tone;

/**
 * {@link AbstractMapper Mapper} for {@link Note}.
 */
public abstract class NoteMapper extends AbstractValuedItemMapper<Note> {

  @Override
  protected Note readItem(MusicInputStream in, SongFormatContext context, List<MusicalDecoration> decorations) {

    Tone tone = getToneMapper().read(in, context);
    if (tone == null) {
      return null;
    }
    // TODO consider unit note length
    MusicalValue value = getValueMapper().read(in, context);
    if (value == null) {
      value = MusicalValue._1_4;
    } else if (value == MusicalValue._1_1) {
      value = MusicalValue._4_4;
    }
    return new Note(tone, value, decorations);
  }

  @Override
  protected void writeItem(Note note, MusicOutputStream out, SongFormatContext context) {

    getToneMapper().write(note.getTone(), out, context);
  }
}
